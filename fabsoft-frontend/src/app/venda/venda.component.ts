import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { Venda } from '../model/venda';
import { Cliente } from '../model/cliente';
import { Lote } from '../model/lote';
import { VendaService } from '../service/venda.service';
import { ClienteService } from '../service/cliente.service';
import { LoteService } from '../service/lote.service';

@Component({
  selector: 'app-venda',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './venda.html',
  styleUrls: ['./venda.css'],
  providers: [VendaService, ClienteService, LoteService]
})
export class VendaComponent implements OnInit {

  venda: Venda = {
    valorTotalNegociado: 0,
    formaPagamento: '',
    status: 'Concluída',
    cliente: undefined,
    lote: undefined
  };

  listaVendas: Venda[] = [];
  listaClientes: Cliente[] = [];
  listaLotes: Lote[] = [];

  constructor(
    private service: VendaService,
    private clienteService: ClienteService,
    private loteService: LoteService
  ) {}

  ngOnInit(): void {
    this.findAllVendas();
    this.findAllClientes();
    this.findAllLotes();
  }

  findAllVendas(): void {
    this.service.findAll().subscribe({
      next: (data) => this.listaVendas = data,
      error: () => alert('Erro ao carregar vendas.')
    });
  }

  findAllClientes(): void {
    this.clienteService.findAll().subscribe({
      next: (data) => this.listaClientes = data
    });
  }

  findAllLotes(): void {
    this.loteService.findAll().subscribe({
      next: (data) => this.listaLotes = data
    });
  }

  save(): void {
    if (!this.venda.cliente || !this.venda.lote) {
      alert('Selecione Cliente e Lote!');
      return;
    }

    this.venda.dataVenda = new Date().toISOString().split('T')[0];

    
    if (this.venda.valorTotalNegociado === 0 && this.venda.lote.valorTotalBase) {
      this.venda.valorTotalNegociado = this.venda.lote.valorTotalBase;
    }

    this.service.save(this.venda).subscribe({
      next: () => {
        alert('Venda realizada!');
        this.findAllVendas();
        this.clean();
      },
      error: (err) => {
        console.error(err);
        alert('Erro ao salvar venda.');
      }
    });
  }

 
  delete(id: number): void {
    if (confirm('Deseja cancelar esta venda?')) {
      this.service.delete(id).subscribe({
        next: () => this.findAllVendas(),
        error: () => alert('Erro ao excluir venda')
      });
    }
  }

  clean(): void {
    this.venda = {
      valorTotalNegociado: 0,
      formaPagamento: '',
      status: 'Concluída',
      cliente: undefined,
      lote: undefined
    };
  }
}