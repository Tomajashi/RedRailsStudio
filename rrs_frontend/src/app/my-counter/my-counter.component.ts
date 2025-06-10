import { Component } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { increment, decrement, reset } from '../counter.actions';
import { CommonModule } from '@angular/common'; // Import CommonModule if needed
import {selectCount} from '../counter.selector'; // Import the selector if you have one

@Component({
  selector: 'app-my-counter',
  templateUrl: './my-counter.component.html',
  standalone: true,
  imports: [CommonModule] // Add any necessary imports here, e.g., CommonModule if needed
})
export class MyCounterComponent {
  count$: Observable<number>;

  constructor(private store: Store<{ count: number }>) {
    this.count$ = store.select('count');
  }

  increment() {
    this.store.dispatch(increment());
  }

  decrement() {
    this.store.dispatch(decrement());
  }

  reset() {
    this.store.dispatch(reset());
  }
}