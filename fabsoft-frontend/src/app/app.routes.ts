import { Routes } from '@angular/router';
import { ClienteComponent } from './cliente/cliente.component';
import { EmpreendimentoComponent } from './empreendimento/empreendimento.component';
import { LoteComponent } from './lote/lote.component';
import { VendaComponent } from './venda/venda.component';
import { HomeComponent } from './home/home.component';

export const routes: Routes = [
    // Rota Raiz: Abre a Home quando o caminho é vazio
    { path: '', component: HomeComponent },
    
    // Rotas das funcionalidades
    { path: 'clientes', component: ClienteComponent },
    { path: 'empreendimentos', component: EmpreendimentoComponent },
    { path: 'lotes', component: LoteComponent },
    { path: 'vendas', component: VendaComponent }
];