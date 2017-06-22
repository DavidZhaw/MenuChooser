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
            "AddPerson": "Add Person",
            "Begin": "Begin",
            "Comments": "Comments",
            "Completed": "Completed",
            "Date": "Date",
            "Duration": "Duration (h)",
            "EditPerson": "Edit Person",
            "EditProject": "Edit Project",
            "EditTask": "Edit Task",
            "EMail": "E-Mail",
            "End": "End",
            "FirstName": "FirstName",
            "LastName": "LastName",
            "Month": "Month",
            "Name": "Name",
            "NewPerson": "New Person",
            "NewProject": "New Project",
            "NewTask": "New Task",
            "NewWork": "New Work",
            "NewWorkload": "New Workload",
            "Person": "Person",
            "Persons": "Persons",
            "Planned": "Planned (h)",
            "Project": "Project",
            "Projects": "Projects",
            "RemovePerson": "Remove Person",
            "Task": "Task",
            "Tasks": "Tasks",
            "Work": "Work",
            "Workload": "Workload",
            "Year": "Year",
        }
    }
}