/* angular/path imports */
import {Component} from '@angular/core';
import * as path from "path-framework/app/path-framework/path";

/* model imports */
import {GuiModel} from './gui-model/guimodel';
import {TranslationService} from "path-framework/app/path-framework/service/translation.service";
import {AlcoholTesterTranslationService} from "./alcohol-tester-translation-service";

@Component({
    selector: 'path-application',
    templateUrl: './../../node_modules/path-framework/app/path-framework/path-app.component.html',
    // providers: [{ provide: path.PathService, useClass: path.PathMockService }]
    providers: [path.PathService, { provide: TranslationService, useClass: AlcoholTesterTranslationService }]
})
export class AlcoholTesterAppComponent extends path.PathAppComponent {

    private _appConfig = new GuiModel();

    constructor(pathService: path.PathService, translationService: TranslationService) {
        super(pathService, translationService);
    }

    protected getFrontendVersion():string {
        return "0.1.0-SNAPSHOT";
    }

    protected getStartPage():string {
        return "mainmenu";
    }

    protected getApplicationLogo():string {
        return null;
    }

    protected getOwnUserForm():string {
        return "OwnUserForm";
    }

    protected getGuiModel() {
        if (this._appConfig != null) {
            return this._appConfig.guiModel;
        }
        return null;
    }

    public getBackendUrl() {
        if (window.location.hostname.indexOf("herokuapp") != -1) {
            return "https://alcoholtester.herokuapp.com/services";
        }
        return "http://localhost:4567/services";
    }
    
    protected getBeans() {
        return {};
    }
    
    protected getHandlers() {
        return {};
    }

}