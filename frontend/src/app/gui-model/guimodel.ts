export class GuiModel {

    private _guiModel = {
        "application": {
            "title": "Alcohol Tester",
            "formList": [
                {
                    "id": "OwnUserForm",
                    "title": "NotImplemented",
                    "formFieldList": [
                        {
                            "type": "okButton",
                            "name": "Ok"
                        }
                    ]
                },
                {
                    "id": "PersonForm",
                    "title": "Person",
                    "url": "/person",
                    "formFieldList": [
                        {
                            "id": "weight",
                            "type": "number",
                            "name": "Weight",
                            "width": 2,
                            "min": 1,
                            "max": 999,
                            "digits": 0,
                            "required": true
                        },
                        {
                            "id": "isFemale",
                            "type": "RadioGroupField",
                            "name": "Sex",
                            "defaultKey": false,
                            "radios": [{
                                type: "radio",
                                name: "Mann",
                                key: false
                            }, {
                                type: "radio",
                                name: "Frau",
                                key: true
                            }
                            ]
                        },
                        {
                            "type": "cancelButton",
                            "name": "Cancel"
                        },
                        {
                            "type": "okButton",
                            "name": "Ok"
                        }
                    ]
                },
            ],
            "pageList": [
                {
                    "id": "mainmenu",
                    "name": "MainMenu",
                    "elementList": [
                        {
                            "type": "list",
                            "name": "Person",
                            "icon": "fa-user",
                            "color": "green",
                            "width": 1,
                            "form": {
                                "form": "PersonForm",
                            },
                            "data": [ { key: { key: "1" }, name: "Person" } ],
                        },
                        {
                            "type": "list",
                            "name": "Level",
                            "icon": "fa-thermometer-half",
                            "color": "blue",
                            "search": false,
                            "url": "/level"
                        },
                        {
                            "type": "list",
                            "name": "Konsum List",
                            "icon": "fa-glass",
                            "color": "purple",
                            "search": false,
                            "url": "/konsum"
                        },
                        {
                            "type": "button",
                            "name": "Reset",
                            "icon": "fa-trash",
                            "color": "red",
                            "url": "/reset",
                        },
                    ]
                }
            ]
        }
    };


    get guiModel() {
        return this._guiModel;
    }
}