import { Empreendimento } from "./empreendimento";

export interface Lote {
    id?: number;
    identificador: string;
    areaM2: number;
    valorTotalBase: number;
    tipo: string;
    empreendimento?: Empreendimento;
}