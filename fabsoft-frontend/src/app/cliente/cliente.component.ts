import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Cliente } from '../model/cliente';
import { ClienteService } from '../service/cliente.service';

@Component({
  selector: 'app-cliente',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.css']
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
  modoEdicao: boolean = false; 

  constructor(private service: ClienteService) {}

  ngOnInit(): void {
    this.findAll();
  }

  
  findAll(): void {
    this.service.findAll().subscribe({
      next: (data) => this.listaClientes = data,
      error: (err) => {
        console.error('Erro ao carregar clientes:', err);
        alert('Erro ao carregar clientes');
      }
    });
  }

  
  iniciarCadastro(): void {
    this.clean();
    this.modoEdicao = true;
  }

  
  iniciarEdicao(cliente: Cliente): void {
    this.cliente = { ...cliente }; 
    this.modoEdicao = true;
  }

  
  cancelar(): void {
    this.modoEdicao = false;
    this.clean();
  }

  save(): void {
    
    const clienteLimpo: Cliente = {
      nomeCompleto: this.cliente.nomeCompleto,
      cpfCnpj: this.cliente.cpfCnpj,
      email: this.cliente.email,
      telefone: this.cliente.telefone,
      endereco: this.cliente.endereco
    };

    if (this.cliente.id) {
      
      clienteLimpo.id = this.cliente.id;
      
      this.service.update(clienteLimpo).subscribe({
        next: () => {
          alert('Cliente atualizado com sucesso!');
          this.modoEdicao = false; 
          this.findAll(); 
          this.clean(); 
        },
        error: (err) => {
          console.error('Erro ao atualizar:', err);
          alert('Erro ao atualizar cliente.');
        }
      });
    } else {
      
      this.service.save(clienteLimpo).subscribe({
        next: () => {
          alert('Cliente cadastrado com sucesso!');
          this.modoEdicao = false; 
          this.findAll(); 
          this.clean(); 
        },
        error: (err) => {
          console.error('Erro ao cadastrar:', err);
          alert('Erro ao cadastrar cliente.');
        }
      });
    }
  }

  
  delete(id: number): void {
    if (confirm('Tem certeza que deseja excluir este cliente?')) {
      this.service.delete(id).subscribe({
        next: () => this.findAll(),
        error: (err) => {
          console.error('Erro ao excluir:', err);
          alert('Erro ao excluir cliente.');
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