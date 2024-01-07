import {EventEmitter, Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SharedService {

  refreshEvent: EventEmitter<string> = new EventEmitter();

  constructor() {
  }

  emitNavChangeEvent(str: string) {
    this.refreshEvent.emit(str);
  }

  getNavChangeEmitter() {
    return this.refreshEvent;
  }
}
