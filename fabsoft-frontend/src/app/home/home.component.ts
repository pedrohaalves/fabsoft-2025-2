import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms'; // Necessário para o <select>
import { LoteService } from '../service/lote.service';
import { EmpreendimentoService } from '../service/empreendimento.service';
import { Lote } from '../model/lote';
import { Empreendimento } from '../model/empreendimento';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  listaLotes: Lote[] = [];
  listaEmpreendimentos: Empreendimento[] = [];
  
  // Controle da seleção
  empSelecionadoId: number | undefined;
  
  // Configuração atual (imagem e lotes) que está sendo exibida
  imagemAtual: string = '';
  posicoesAtuais: any[] = [];

  // BANCO DE DADOS DE MAPAS (Simulado no Front)
  // Aqui você define qual imagem e quais coordenadas pertencem a qual ID de Empreendimento
  mapasConfig: any = {
    // ID 1 (O seu empreendimento principal)
    1: {
      imagem: 'planta.png',
      posicoes: [
        // --- SEUS LOTES CONFIGURADOS ---
        { identificador: 'Quadra A - Lote 01', top: '18%', left: '5.5%', },
        { identificador: 'Quadra A - Lote 02', top: '28%', left: '5.5%',  },
        { identificador: 'Quadra A - Lote 03', top: '38%', left: '5%',  },
        { identificador: 'Quadra A - Lote 04', top: '47%', left: '4.2%',  },
        { identificador: 'Quadra A - Lote 05', top: '57%', left: '4%',  },
        { identificador: 'Quadra A - Lote 06', top: '66%', left: '3%', },

        { identificador: 'Quadra B - Lote 01', top: '20%', left: '45.1%',  },
        { identificador: 'Quadra B - Lote 02', top: '20%', left: '52.5%',  },
        { identificador: 'Quadra B - Lote 03', top: '20%', left: '59.5%',  },
        { identificador: 'Quadra B - Lote 04', top: '20%', left: '66.5%',  },

        { identificador: 'Quadra C - Lote 01', top: '5.7%', left: '13.6%',  },
        { identificador: 'Quadra C - Lote 02', top: '6.3%', left: '20.4%', },
        { identificador: 'Quadra C - Lote 03', top: '6.1%', left: '27.1%',  },
        { identificador: 'Quadra C - Lote 04', top: '5.7%', left: '34.2%', },

        { identificador: 'Quadra D - Lote 01', top: '92%', left: '9%',  },
        { identificador: 'Quadra D - Lote 02', top: '92%', left: '16%',  },
        { identificador: 'Quadra D - Lote 03', top: '92%', left: '23%',  },
        { identificador: 'Quadra D - Lote 04', top: '92%', left: '30%',  },
        { identificador: 'Quadra D - Lote 05', top: '92%', left: '40%',  },
        { identificador: 'Quadra D - Lote 06', top: '92%', left: '50%',  },
        { identificador: 'Quadra D - Lote 07', top: '92%', left: '57%',  },
      ]
    }
    // Se tiver ID 2, adicione aqui:
    // 2: { imagem: 'outra-planta.jpg', posicoes: [...] }
  };

  constructor(
    private loteService: LoteService,
    private empService: EmpreendimentoService
  ) {}

  ngOnInit(): void {
    // 1. Carrega Lotes
    this.loteService.findAll().subscribe(dados => {
      this.listaLotes = dados;
    });

    // 2. Carrega Empreendimentos e seleciona o primeiro
    this.empService.findAll().subscribe(dados => {
      this.listaEmpreendimentos = dados;
      if (this.listaEmpreendimentos.length > 0) {
        // Seleciona o primeiro automaticamente
        this.selecionarEmpreendimento(this.listaEmpreendimentos[0].id!);
      }
    });
  }

  // Função chamada quando o usuário troca o select
  selecionarEmpreendimento(id: number): void {
    this.empSelecionadoId = id;
    const config = this.mapasConfig[id];

    if (config) {
      this.imagemAtual = config.imagem;
      this.posicoesAtuais = config.posicoes;
    } else {
      // Caso não tenha mapa configurado para esse ID
      this.imagemAtual = '';
      this.posicoesAtuais = [];
    }
  }

  getLotePorIdentificador(identificador: string): Lote | undefined {
    // Filtra para garantir que pegamos o lote do empreendimento certo
    // (Caso existam nomes iguais em empreendimentos diferentes)
    return this.listaLotes.find(l => 
      l.identificador === identificador && 
      l.empreendimento?.id == this.empSelecionadoId
    );
  }

  getCor(identificador: string): string {
    const lote = this.getLotePorIdentificador(identificador);
    if (!lote) return 'bg-secondary'; 

    switch (lote.tipo) {
      case 'Disponível': return 'bg-success';
      case 'Vendido': return 'bg-primary'; 
      case 'Atrasado': return 'bg-warning text-dark';
      case 'Reservado': return 'bg-dark';
      default: return 'bg-light text-dark';
    }
  }

  clicarLote(identificador: string): void {
    const lote = this.getLotePorIdentificador(identificador);
    if (lote) {
      alert(`🏠 ${lote.identificador}\n📏 Área: ${lote.areaM2}m²\n💲 Valor: R$ ${lote.valorTotalBase}\n📌 Status: ${lote.tipo}`);
    } else {
      if(confirm(`O lote "${identificador}" não está cadastrado neste empreendimento.\nCopiar nome?`)) {
        navigator.clipboard.writeText(identificador);
      }
    }
  }

  obterCoordenadas(event: MouseEvent): void {
    const imagem = event.target as HTMLElement;
    const rect = imagem.getBoundingClientRect();
    const x = ((event.clientX - rect.left) / rect.width) * 100;
    const y = ((event.clientY - rect.top) / rect.height) * 100;
    console.log(`{ identificador: 'NOVO', top: '${y.toFixed(1)}%', left: '${x.toFixed(1)}%', width: '6%', height: '8%' },`);
  }
}