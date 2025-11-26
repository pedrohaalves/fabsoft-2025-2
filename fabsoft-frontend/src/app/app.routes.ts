import { Routes } from '@angular/router';
import { ClienteComponent } from './cliente/cliente.component';
import { EmpreendimentoComponent } from './empreendimento/empreendimento.component';
import { LoteComponent } from './lote/lote.component';
import { VendaComponent } from './venda/venda.component';

export const routes: Routes = [
    {path:'clientes', component: ClienteComponent},
    { path: 'empreendimentos', component: EmpreendimentoComponent },
    { path: 'lotes', component: LoteComponent },
    {path: 'vendas', component: VendaComponent}
];
