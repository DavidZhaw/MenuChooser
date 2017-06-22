import {NgModule} from '@angular/core';
import {AlcoholTesterAppComponent} from './alcohol-tester-app.component';
import {AppModule} from 'path-framework/app/app.module';

@NgModule({
    imports:      [AppModule],
    declarations: [AlcoholTesterAppComponent],
    bootstrap:    [AlcoholTesterAppComponent],
})
export class AlcoholTesterAppModule {}