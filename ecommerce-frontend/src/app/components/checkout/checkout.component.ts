import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CartItem } from '../../models/cart-item';
import { CartService } from '../../services/cart.service';
import { getOrdersApiUrl, API_CONFIG } from '../../config/api.config';

@Component({
  selector: 'app-checkout',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './checkout.component.html',
  styleUrl: './checkout.component.scss'
})
export class CheckoutComponent implements OnInit {
  cartItems: CartItem[] = [];
  total: number = 0;
  checkoutForm: FormGroup;
  loading: boolean = false;
  submitted: boolean = false;

  constructor(
    private cartService: CartService,
    private router: Router,
    private http: HttpClient,
    private fb: FormBuilder
  ) {
    this.checkoutForm = this.fb.group({
      customerName: ['Lucas', [Validators.required, Validators.minLength(3)]],
      customerEmail: ['teste@teste.com', [Validators.required, Validators.email]],
      customerPhone: ['(11) 99999-9999', [Validators.required, Validators.pattern(/^\(\d{2}\) \d{5}-\d{4}$/)]],
      shippingAddress: ['Rua das Flores, 123 - Apto 45, Centro, São Paulo - SP, CEP: 01234-567', [Validators.required, Validators.minLength(10)]],
      paymentMethod: ['credit_card', Validators.required]
    });
  }

  ngOnInit(): void {
    this.cartService.getCartItems().subscribe(items => {
      this.cartItems = items;
      this.total = this.cartService.getCartTotal();

      if (this.cartItems.length === 0) {
        this.router.navigate(['/cart']);
      }
    });
  }

  get f() {
    return this.checkoutForm.controls;
  }

  formatPrice(price: number): string {
    return new Intl.NumberFormat('pt-BR', {
      style: 'currency',
      currency: 'BRL'
    }).format(price);
  }

  onSubmit(): void {
    this.submitted = true;

    if (this.checkoutForm.invalid) {
      return;
    }

    this.loading = true;

    const orderData = {
      items: this.cartItems.map(item => ({
        productId: item.product.id,
        quantity: item.quantity
      }))
    };

    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    const apiUrl = getOrdersApiUrl(API_CONFIG.ENDPOINTS.ORDERS);

    this.http.post(apiUrl, orderData, { headers })
      .subscribe({
        next: (response) => {
          console.log('Pedido criado com sucesso:', response);
          this.cartService.clearCart();
          alert('Pedido realizado com sucesso! Você receberá um email de confirmação.');
          this.router.navigate(['/']);
        },
        error: (error) => {
          console.error('Erro ao criar pedido:', error);
          alert('Erro ao processar o pedido. Tente novamente.');
          this.loading = false;
        }
      });
  }

  goBack(): void {
    this.router.navigate(['/cart']);
  }
}
