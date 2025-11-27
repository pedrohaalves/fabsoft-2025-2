import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Venda } from '../model/venda';

@Injectable({
  providedIn: 'root'
})
export class VendaService {

  private readonly API = 'http://localhost:8080/api/v1/vendas';

  constructor(private http: HttpClient) { }

  findAll(): Observable<Venda[]> {
    return this.http.get<Venda[]>(this.API);
  }

  save(venda: Venda): Observable<Venda> {
  
    return this.http.post<Venda>(this.API, venda);
  }

  
  update(venda: Venda): Observable<Venda> {
    return this.http.put<Venda>(`${this.API}/${venda.id}`, venda);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API}/${id}`);
  }
  
}