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
        return "0.0.6-SNAPSHOT";
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
        if (window.location.hostname.indexOf("dev.herokuapp") != -1) {
            return "http://iwitask-dev.herokuapp.com/services";
        } else if (window.location.hostname.indexOf("test.herokuapp") != -1) {
            return "http://iwitask-test.herokuapp.com/services";
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