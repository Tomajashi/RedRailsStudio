import { Store } from '@ngrx/store';
import { AppState } from './app.state';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import {Resources} from '../models/resources.model';
import {TrainsInfo} from '../models/trainsInfo.model';
import { selectTrainsInfo, selectResources } from './selectors';
import { iUser } from '../models/user.model';
import { selectUser } from './selectors/user.selectors';



@Injectable({
  providedIn: 'root' //
})
export class StoreService {
    TrainsInfo$: Observable<Trainsinfo>;

    constructor(private store: Store<AppState>) {
        this.user$ = this.store.pipe(select(selectUser));
    }

    updateUser(user: user) {
        this.store.dispatch(updateUser({ user }));
    }
}