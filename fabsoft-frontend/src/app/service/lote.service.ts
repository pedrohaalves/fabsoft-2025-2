import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Lote } from '../model/lote';

@Injectable({
  providedIn: 'root'
})
export class LoteService {

  private readonly API = 'http://localhost:8080/api/v1/lotes';

  constructor(private http: HttpClient) { }

  findAll(): Observable<Lote[]> {
    return this.http.get<Lote[]>(this.API);
  }

  save(lote: Lote): Observable<Lote> {
    return this.http.post<Lote>(this.API, lote);
  }

  update(lote: Lote): Observable<Lote> {
    return this.http.put<Lote>(`${this.API}/${lote.id}`, lote);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API}/${id}`);
  }
}