import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Empreendimento } from '../model/empreendimento';

@Injectable({
  providedIn: 'root'
})
export class EmpreendimentoService {

  private readonly API = 'http://localhost:8080/api/v1/empreendimentos';

  constructor(private http: HttpClient) { }

  findAll(): Observable<Empreendimento[]> {
    return this.http.get<Empreendimento[]>(this.API);
  }

  findById(id: number): Observable<Empreendimento> {
    return this.http.get<Empreendimento>(`${this.API}/${id}`);
  }

  save(empreendimento: Empreendimento): Observable<Empreendimento> {
    return this.http.post<Empreendimento>(this.API, empreendimento);
  }

  update(empreendimento: Empreendimento): Observable<Empreendimento> {
    return this.http.put<Empreendimento>(`${this.API}/${empreendimento.id}`, empreendimento);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API}/${id}`);
  }
}