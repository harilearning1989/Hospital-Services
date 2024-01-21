import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DefaultDataService {

  constructor(private http: HttpClient) {
  }

  employees: Array<any> = [
    { id: 1, name: 'hardik', age: 29, salary: '60000' },
    { id: 2, name: 'parth', age: 27, salary: '50000' },
    { id: 3, name: 'deepak', age: 29, salary: '50000' },
    { id: 4, name: 'john', age: 29, salary: '50000' }
  ];

  order: boolean = false;

  sortData() {
    if (this.order) {
      this.employees = this.employees.sort((i, j) => (j.id > i.id ? -1 : 1));
    }
    else {
      this.employees = this.employees.sort((i, j) => (j.id > i.id ? 1 : -1));
    }
    this.order = !this.order;
    console.log(this.order);
  }

  filterByText(initial: string) {
    this.employees = this.employees.filter(i => i.name.toLowerCase().indexOf(initial.toLocaleLowerCase()) !== -1);
    console.log(this.employees);
  }


  public getSpecialists(): Observable<any> {
    return this.http.get("./assets/Specialists.json");
  }
}
