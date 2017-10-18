export class GuiModel {

    private _guiModel = {
        "application": {
            "title": "Menu Chooser",
            "formList": [
                
          ],
            "pageList": [
                {
                    "id": "mainmenu",
                    "name": "Proposed Menus",
                    "elementList": [
                        {
                            "type": "list",
                            "name": "Konsum List",
                            "icon": "fa-glass",
                            "color": "purple",
                            "search": false,
                            "url": "/getmenus"
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