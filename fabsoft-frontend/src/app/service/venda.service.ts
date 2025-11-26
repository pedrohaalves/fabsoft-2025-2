import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Venda } from '../model/venda';

@Injectable({
  providedIn: 'root'
})
export class VendaService {

  
  private readonly API = 'http://localhost:8080/api/v1/vendas';

  
  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) { }

  findAll(): Observable<Venda[]> {
    return this.http.get<Venda[]>(this.API);
  }

  // Aplicando os headers no POST
  save(venda: Venda): Observable<Venda> {
    return this.http.post<Venda>(this.API, venda, this.httpOptions);
  }

  findById(id: number): Observable<Venda> {
    return this.http.get<Venda>(`${this.API}/${id}`);
  }
  
  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API}/${id}`);
  }
}