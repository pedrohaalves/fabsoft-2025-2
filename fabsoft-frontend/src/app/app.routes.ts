import { Routes } from '@angular/router';
import { ClienteComponent } from './cliente/cliente.component';
import { EmpreendimentoComponent } from './empreendimento/empreendimento.component';
import { LoteComponent } from './lote/lote.component';
import { VendaComponent } from './venda/venda.component';
import { HomeComponent } from './home/home.component';

export const routes: Routes = [
    
    { path: '', component: HomeComponent },
    
    { path: 'clientes', component: ClienteComponent },
    { path: 'empreendimentos', component: EmpreendimentoComponent },
    { path: 'lotes', component: LoteComponent },
    { path: 'vendas', component: VendaComponent }
];