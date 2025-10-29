import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export abstract class GenericService<T, ID> {
  constructor(protected http: HttpClient, protected apiUrl: string) {}

  getAll(): Observable<T[]> {
    return this.http.get<T[]>(`${this.apiUrl}`);
  }

  getById(id: ID): Observable<T> {
    return this.http.get<T>(`${this.apiUrl}/${id}`);
  }

  create(item: T): Observable<T> {
    return this.http.post<T>(`${this.apiUrl}`, item);
  }

  update(id: ID, item: T): Observable<T> {
    return this.http.put<T>(`${this.apiUrl}/${id}`, item);
  }

  delete(id: ID): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
