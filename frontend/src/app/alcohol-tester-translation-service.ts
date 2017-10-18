import {Injectable} from "@angular/core";
import {TranslationService} from "path-framework/app/path-framework/service/translation.service"

@Injectable()
export class AlcoholTesterTranslationService extends TranslationService {

    protected getTranslation(key:string) : string {
        let myTranslations = this.createTranslationMap(this.getAlcoholTesterTranslations());
        // prefer custom translations
        if (myTranslations.get(key) == null) {
            return super.getTranslation(key);
        }
        return myTranslations.get(key);
    }

    private getAlcoholTesterTranslations() {
        let languageCode: string = this.getUserLanguage();

        // put additional application translations here
        return {
            "NotImplemented": "Benutzerverwaltung ist nicht implementiert",
            "Reset": "Reset",
            "ProposedMenus": "Proposed Menus",
        }
    }
}