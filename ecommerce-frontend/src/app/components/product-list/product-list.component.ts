import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Product } from '../../models/product';
import { ProductService } from '../../services/product.service';
import { CartService } from '../../services/cart.service';
import { catchError, finalize, debounceTime, distinctUntilChanged } from 'rxjs/operators';
import { of, Subject } from 'rxjs';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.scss'
})
export class ProductListComponent implements OnInit {
  products: Product[] = [];
  filteredProducts: Product[] = [];
  searchTerm: string = '';
  loading: boolean = true;
  error: string = '';
  private searchSubject = new Subject<string>();

  constructor(
    private productService: ProductService,
    private cartService: CartService
  ) {
    // Configurar busca com debounce
    this.searchSubject.pipe(
      debounceTime(300),
      distinctUntilChanged()
    ).subscribe(searchTerm => {
      this.performSearch(searchTerm);
    });
  }

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    this.loading = true;
    this.error = '';

    this.productService.getProducts().pipe(
      catchError(error => {
        console.error('Erro ao carregar produtos:', error);
        this.error = 'Erro ao carregar produtos. Tente novamente.';
        return of([]);
      }),
      finalize(() => {
        this.loading = false;
      })
    ).subscribe({
      next: (products) => {
        this.products = products;
        this.filteredProducts = products;
      }
    });
  }

  onSearchInput(): void {
    this.searchSubject.next(this.searchTerm);
  }

  searchProducts(): void {
    this.performSearch(this.searchTerm);
  }

  private performSearch(searchTerm: string): void {
    if (!searchTerm.trim()) {
      this.filteredProducts = this.products;
      return;
    }

    // Busca local nos produtos já carregados
    const term = searchTerm.toLowerCase().trim();
    this.filteredProducts = this.products.filter(product =>
      product.name.toLowerCase().includes(term) ||
      product.description.toLowerCase().includes(term) ||
      product.category.toLowerCase().includes(term)
    );
  }

  filterByCategory(category: string): void {
    if (!category || category === 'all') {
      this.filteredProducts = this.products;
      return;
    }

    this.filteredProducts = this.products.filter(product =>
      product.category.toLowerCase() === category.toLowerCase()
    );
  }

  clearSearch(): void {
    this.searchTerm = '';
    this.filteredProducts = this.products;
  }

  addToCart(product: Product): void {
    this.cartService.addToCart(product, 1);
    // Aqui você pode adicionar uma notificação de sucesso
    console.log(`${product.name} adicionado ao carrinho!`);
  }

  formatPrice(price: number): string {
    return new Intl.NumberFormat('pt-BR', {
      style: 'currency',
      currency: 'BRL'
    }).format(price);
  }

  retryLoad(): void {
    this.loadProducts();
  }

  onImageError(event: Event): void {
    const target = event.target as HTMLImageElement;
    if (target) {
      target.src = 'https://images.unsplash.com/photo-1560472354-b33ff0c44a43?w=400&h=300&fit=crop&crop=center';
    }
  }
}
