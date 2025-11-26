import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { Lote } from '../model/lote';
import { Empreendimento } from '../model/empreendimento';
import { LoteService } from '../service/lote.service';
import { EmpreendimentoService } from '../service/empreendimento.service';

@Component({
  selector: 'app-lote',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './lote.html',
  styleUrl: './lote.css',
  providers: [LoteService, EmpreendimentoService]
})
export class LoteComponent implements OnInit {

  lote: Lote = {
    identificador: '',
    areaM2: 0,
    valorTotalBase: 0,
    tipo: 'Disponível',
    empreendimento: undefined
  };

  listaLotes: Lote[] = [];
  listaEmpreendimentos: Empreendimento[] = [];

  constructor(
    private service: LoteService,
    private empService: EmpreendimentoService
  ) {}

  ngOnInit(): void {
    this.findAllLotes();
    this.findAllEmpreendimentos();
  }

  findAllLotes(): void {
    this.service.findAll().subscribe({
      next: (data) => this.listaLotes = data,
      error: (err) => alert('Erro ao carregar lotes')
    });
  }

  findAllEmpreendimentos(): void {
    this.empService.findAll().subscribe({
      next: (data) => this.listaEmpreendimentos = data,
      error: (err) => alert('Erro ao carregar empreendimentos')
    });
  }

  save(): void {
    if (this.lote.id) {
      this.service.update(this.lote).subscribe({
        next: () => {
          alert('Lote atualizado!');
          this.findAllLotes();
          this.clean();
        },
        error: () => alert('Erro ao atualizar')
      });
    } else {
      this.service.save(this.lote).subscribe({
        next: () => {
          alert('Lote criado!');
          this.findAllLotes();
          this.clean();
        },
        error: () => alert('Erro ao salvar')
      });
    }
  }

  delete(id: number): void {
    if (confirm('Excluir lote?')) {
      this.service.delete(id).subscribe({
        next: () => this.findAllLotes(),
        error: () => alert('Erro ao excluir')
      });
    }
  }

  select(lote: Lote): void {
    this.lote = { ...lote };
    
    if(lote.empreendimento) {
      this.lote.empreendimento = this.listaEmpreendimentos.find(e => e.id === lote.empreendimento?.id);
    }
  }

  clean(): void {
    this.lote = {
      identificador: '',
      areaM2: 0,
      valorTotalBase: 0,
      tipo: 'Disponível',
      empreendimento: undefined
    };
  }

  // Função atualizada para o Mapa de Calor Financeiro
  getCorStatus(tipo: string): string {
    switch (tipo) {
      case 'Disponível':
        return 'bg-success text-white'; // Verde (Tudo certo para vender)
      case 'Vendido':
        return 'bg-primary text-white'; // Azul (Vendido e pagando certinho)
      case 'Atrasado':
        return 'bg-warning text-dark';  // Amarelo (Alerta: Inadimplente!)
      case 'Reservado':
        return 'bg-secondary text-white'; // Cinza (Aguardando)
      default:
        return 'bg-light text-dark'; // Branco (Outros)
    }
  }
  }
