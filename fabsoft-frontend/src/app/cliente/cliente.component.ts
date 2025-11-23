import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { Cliente } from '../model/cliente';
import { ClienteService } from '../service/cliente.service';

@Component({
  selector: 'app-cliente',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './cliente.html',
  styleUrl: './cliente.css',
  providers: [ClienteService]
})
export class ClienteComponent implements OnInit {

  cliente: Cliente = {
    nomeCompleto: '',
    cpfCnpj: '',
    email: '',
    telefone: '',
    endereco: ''
  };

  listaClientes: Cliente[] = [];

  constructor(private service: ClienteService) {}

  ngOnInit(): void {
    this.findAll();
  }

  findAll(): void {
    this.service.findAll().subscribe({
      next: (resposta) => {
        this.listaClientes = resposta;
      },
      error: (erro) => {
        console.error(erro);
        alert('Erro ao carregar clientes.');
      }
    });
  }

  save(): void {
    if (this.cliente.id) {
      this.service.update(this.cliente).subscribe({
        next: (resposta) => {
          this.findAll();
          alert('Cliente atualizado com sucesso!');
          this.clean();
        },
        error: (erro) => {
          console.error(erro);
          alert('Erro ao atualizar cliente.');
        }
      });
    } else {
      this.service.save(this.cliente).subscribe({
        next: (resposta) => {
          this.listaClientes.push(resposta);
          alert('Cliente cadastrado com sucesso!');
          this.clean();
        },
        error: (erro) => {
          console.error(erro);
          alert('Erro ao salvar cliente.');
        }
      });
    }
  }

  select(cliente: Cliente): void {
    this.cliente = { ...cliente };
  }

  delete(id: number): void {
    if (confirm('Tem certeza que deseja excluir este cliente?')) {
      this.service.delete(id).subscribe({
        next: () => {
          this.findAll();
          alert('Cliente excluído!');
        },
        error: (erro) => {
          console.error(erro);
          alert('Erro ao excluir.');
        }
      });
    }
  }

  clean(): void {
    this.cliente = {
      nomeCompleto: '',
      cpfCnpj: '',
      email: '',
      telefone: '',
      endereco: ''
    };
  }
}