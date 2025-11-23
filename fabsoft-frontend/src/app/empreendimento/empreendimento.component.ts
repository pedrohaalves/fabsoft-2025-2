import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { Empreendimento } from '../model/empreendimento';
import { EmpreendimentoService } from '../service/empreendimento.service';

@Component({
  selector: 'app-empreendimento',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './empreendimento.html',
  styleUrl: './empreendimento.css',
  providers: [EmpreendimentoService]
})
export class EmpreendimentoComponent implements OnInit {

  empreendimento: Empreendimento = {
    nome: '',
    localizacao: '',
    descricao: '',
    status: ''
  };

  listaEmpreendimentos: Empreendimento[] = [];

  constructor(private service: EmpreendimentoService) {}

  ngOnInit(): void {
    this.findAll();
  }

  findAll(): void {
    this.service.findAll().subscribe({
      next: (resposta) => {
        this.listaEmpreendimentos = resposta;
      },
      error: (erro) => {
        console.error(erro);
        alert('Erro ao carregar empreendimentos.');
      }
    });
  }

  save(): void {
    if (this.empreendimento.id) {
      this.service.update(this.empreendimento).subscribe({
        next: (resposta) => {
          this.findAll();
          alert('Empreendimento atualizado!');
          this.clean();
        },
        error: (erro) => {
          console.error(erro);
          alert('Erro ao atualizar.');
        }
      });
    } else {
      this.service.save(this.empreendimento).subscribe({
        next: (resposta) => {
          this.listaEmpreendimentos.push(resposta);
          alert('Empreendimento cadastrado!');
          this.clean();
        },
        error: (erro) => {
          console.error(erro);
          alert('Erro ao salvar.');
        }
      });
    }
  }

  select(item: Empreendimento): void {
    this.empreendimento = { ...item };
  }

  delete(id: number): void {
    if (confirm('Confirma a exclusão?')) {
      this.service.delete(id).subscribe({
        next: () => {
          this.findAll();
          alert('Excluído com sucesso!');
        },
        error: (erro) => {
          console.error(erro);
          alert('Erro ao excluir.');
        }
      });
    }
  }

  clean(): void {
    this.empreendimento = {
      nome: '',
      localizacao: '',
      descricao: '',
      status: ''
    };
  }
}