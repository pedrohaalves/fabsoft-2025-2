import { Cliente } from "./cliente";
import { Lote } from "./lote";

export interface Venda {
    id?: number;
    dataVenda?: string;
    valorTotalNegociado: number;
    formaPagamento: string;
    status: string;
    cliente?: Cliente;
    lote?: Lote;
}