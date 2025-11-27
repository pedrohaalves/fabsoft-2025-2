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

  // Objeto que guarda os dados do formulário
  cliente: Cliente = {
    nomeCompleto: '',
    cpfCnpj: '',
    email: '',
    telefone: '',
    endereco: ''
  };

  listaClientes: Cliente[] = [];
  modoEdicao: boolean = false; // Controla se mostra a Tabela (false) ou Formulário (true)

  constructor(private service: ClienteService) {}

  ngOnInit(): void {
    this.findAll();
  }

  // 1. Busca todos os clientes do Backend
  findAll(): void {
    this.service.findAll().subscribe({
      next: (data) => this.listaClientes = data,
      error: (err) => {
        console.error('Erro ao carregar clientes:', err);
        alert('Erro ao carregar clientes');
      }
    });
  }

  // 2. Prepara para cadastrar NOVO (Limpa e mostra formulário)
  iniciarCadastro(): void {
    this.clean();
    this.modoEdicao = true;
  }

  // 3. Prepara para EDITAR (Copia dados e mostra formulário)
  iniciarEdicao(cliente: Cliente): void {
    this.cliente = { ...cliente }; // Copia para não alterar a tabela visualmente antes de salvar
    this.modoEdicao = true;
  }

  // 4. Botão Cancelar (Volta para lista)
  cancelar(): void {
    this.modoEdicao = false;
    this.clean();
  }

  // 5. Salvar (Create ou Update)
  save(): void {
    // SANITIZAÇÃO: Cria um objeto novo apenas com os dados puros
    // Isso evita enviar listas extras (vendas) que podem dar erro no Java
    const clienteLimpo: Cliente = {
      nomeCompleto: this.cliente.nomeCompleto,
      cpfCnpj: this.cliente.cpfCnpj,
      email: this.cliente.email,
      telefone: this.cliente.telefone,
      endereco: this.cliente.endereco
    };

    if (this.cliente.id) {
      // Se tem ID, é atualização (PUT)
      clienteLimpo.id = this.cliente.id;
      
      this.service.update(clienteLimpo).subscribe({
        next: () => {
          alert('Cliente atualizado com sucesso!');
          this.modoEdicao = false; // Volta pra lista
          this.findAll(); // Recarrega a lista
          this.clean(); // Limpa o formulário
        },
        error: (err) => {
          console.error('Erro ao atualizar:', err);
          alert('Erro ao atualizar cliente.');
        }
      });
    } else {
      // Se não tem ID, é novo cadastro (POST)
      this.service.save(clienteLimpo).subscribe({
        next: () => {
          alert('Cliente cadastrado com sucesso!');
          this.modoEdicao = false; // Volta pra lista
          this.findAll(); // Recarrega a lista
          this.clean(); // Limpa o formulário
        },
        error: (err) => {
          console.error('Erro ao cadastrar:', err);
          alert('Erro ao cadastrar cliente.');
        }
      });
    }
  }

  // 6. Excluir
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

  // 7. Método para limpar o formulário
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