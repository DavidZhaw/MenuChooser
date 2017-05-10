import {NgModule} from '@angular/core';
import {IwiTaskAppComponent} from './task-app.component';
import {AppModule} from 'path-framework/app/app.module';

@NgModule({
    imports:      [AppModule],
    declarations: [IwiTaskAppComponent],
    bootstrap:    [IwiTaskAppComponent],
})
export class IwiTaskAppModule {}