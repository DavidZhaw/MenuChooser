export class GuiModel {

    private _guiModel = {
        "application": {
            "title": "Menu Chooser",
            "formList": [
                
          ],
            "pageList": [
                {
                    "id": "mainmenu",
                    "name": "ProposedMenus",
                    "elementList": [
                        {
                            "type": "list",
                            "name": "Konsum List",
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