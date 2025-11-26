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
  styleUrl: './cliente.component.css'
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
  modoEdicao: boolean = false; // Controla se mostra a tabela ou o formulário

  constructor(private service: ClienteService) {}

  ngOnInit(): void {
    this.findAll();
  }

  // Busca todos os clientes do Backend
  findAll(): void {
    this.service.findAll().subscribe({
      next: (data) => this.listaClientes = data,
      error: (err) => alert('Erro ao carregar clientes')
    });
  }

  // Entra no modo de CRIAÇÃO (Limpa o formulário e esconde a tabela)
  iniciarCadastro(): void {
    this.clean();
    this.modoEdicao = true;
  }

  // Entra no modo de EDIÇÃO (Preenche o formulário com os dados do cliente clicado)
  iniciarEdicao(cliente: Cliente): void {
    this.cliente = { ...cliente }; // Cria uma cópia para não alterar a lista visualmente antes de salvar
    this.modoEdicao = true;
  }

  // Sai do modo de edição/criação e volta para a tabela
  cancelar(): void {
    this.modoEdicao = false;
    this.clean();
  }

  // Salva ou Atualiza
  save(): void {
    if (this.cliente.id) {
      // ATUALIZAR
      this.service.update(this.cliente).subscribe({
        next: () => {
          alert('Cliente atualizado com sucesso!');
          this.modoEdicao = false; // Volta pra lista automaticamente
          this.findAll(); // Recarrega a lista atualizada
          this.clean();
        },
        error: (err) => alert('Erro ao atualizar cliente')
      });
    } else {
      // CRIAR NOVO
      this.service.save(this.cliente).subscribe({
        next: () => {
          alert('Cliente cadastrado com sucesso!');
          this.modoEdicao = false; // Volta pra lista automaticamente
          this.findAll(); // Recarrega a lista atualizada
          this.clean();
        },
        error: (err) => alert('Erro ao cadastrar cliente')
      });
    }
  }

  // Exclui um cliente
  delete(id: number): void {
    if (confirm('Tem certeza que deseja excluir este cliente?')) {
      this.service.delete(id).subscribe({
        next: () => this.findAll(),
        error: (err) => alert('Erro ao excluir cliente')
      });
    }
  }

  // Limpa o objeto cliente
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