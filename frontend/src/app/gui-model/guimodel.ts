export class GuiModel {

    private _guiModel = {
        "application": {
            "title": "Alcohol Tester",
            "formList": [
                {
                    "id": "OwnUserForm",
                    "title": "Not implemented",
                    "formFieldList": [
                        {
                            "type": "okButton",
                            "name": "Ok"
                        }
                    ]
                },
                {
                    "id": "ProjectForm",
                    "title": "Project",
                    "url": "/project",
                    "formFieldList": [
                        {
                            "id": "name",
                            "type": "text",
                            "name": "Name",
                            "newRow": true,
                            "required": true,
                            "width": 2
                        },
                        {
                            "id": "evtBegin",
                            "type": "date",
                            "name": "Begin",
                            "width": 1,
                            "required": true
                        },
                        {
                            "id": "evtEnd",
                            "type": "date",
                            "name": "End",
                            "width": 1,
                            "required": false
                        },
                        {
                            "id": "comments",
                            "type": "text",
                            "name": "Comments",
                            "maxLength": 4000,
                            "height": 4,
                            "width": 2
                        },
                        {
                            "type": "deleteButton",
                            "name": "Delete"
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
                {
                    "id": "ProjectPersonForm",
                    "title": "AddPerson",
                    "url": "/project/:projectKey/person",
                    "formFieldList": [
                        {
                            "id":   "projectKey",
                            "type": "autocomplete",
                            "name": "Project",
                            "wordSearchEnabled": true,
                            "defaultKey": "projectKey",
                            "readonly": true,
                            "form": "ProjectForm",
                            "url": "/project",
                            "width": 2,
                            "required": true
                        },
                        {
                            "id":   "personKey",
                            "type": "autocomplete",
                            "name": "Person",
                            "wordSearchEnabled": true,
                            "defaultKey": "personKey",
                            "readonly": true,
                            "form": "PersonForm",
                            "url": "/person",
                            "width": 2,
                            "required": true
                        },
                        {
                            "type": "deleteButton",
                            "name": "Delete"
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
                {
                    "id": "TaskForm",
                    "title": "Task",
                    "url": "/task",
                    "formFieldList": [
                        {
                            "id": "name",
                            "type": "text",
                            "name": "Name",
                            "newRow": true,
                            "required": true,
                            "width": 2
                        },
                        {
                            "id":   "project",
                            "type": "autocomplete",
                            "name": "Project",
                            "wordSearchEnabled": true,
                            "defaultKey": "projectKey",
                            "readonly": true,
                            "form": "ProjectForm",
                            "url": "/person/:person/project",
                            "width": 2,
                            "required": true
                        },
                        {
                            "id":   "person",
                            "type": "autocomplete",
                            "name": "Person",
                            "wordSearchEnabled": true,
                            "defaultKey": "personKey",
                            "readonly": true,
                            "form": "PersonForm",
                            "url": "/project/:project/person",
                            "width": 2,
                            "required": true
                        },
                        {
                            "id": "evtBegin",
                            "type": "date",
                            "name": "Begin",
                            "width": 1,
                            "required": true
                        },
                        {
                            "id": "evtEnd",
                            "type": "date",
                            "name": "End",
                            "width": 1,
                            "required": true
                        },
                        {
                            "id": "evtCompleted",
                            "type": "date",
                            "name": "Completed",
                            "width": 2,
                            "required": false
                        },
                        {
                            "id": "planned",
                            "type": "number",
                            "name": "Planned",
                            "width": 2,
                            "required": true,
                            "min": 0,
                            "max": 99999,
                            "digits": 1
                        },
                        {
                            "id": "comments",
                            "type": "text",
                            "name": "Comments",
                            "maxLength": 4000,
                            "height": 4,
                            "width": 2
                        },
                        {
                            "type": "deleteButton",
                            "name": "Delete"
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
                {
                    "id": "WorkForm",
                    "title": "Work",
                    "url": "/work",
                    "formFieldList": [
                        {
                            "id": "name",
                            "type": "text",
                            "name": "Name",
                            "newRow": true,
                            "required": true,
                            "width": 2
                        },
                        {
                            "id":   "task",
                            "type": "autocomplete",
                            "name": "Task",
                            "wordSearchEnabled": true,
                            "defaultKey": "taskKey",
                            "readonly": true,
                            "form": "TaskForm",
                            "url": "/task",
                            "width": 2,
                            "required": true
                        },
                        {
                            "id": "evtDate",
                            "type": "date",
                            "name": "Date",
                            "width": 2,
                            "required": true
                        },
                        {
                            "id": "duration",
                            "type": "number",
                            "name": "Duration",
                            "width": 2,
                            "required": true,
                            "min": 0,
                            "max": 99999,
                            "digits": 1
                        },
                        {
                            "id": "comments",
                            "type": "text",
                            "name": "Comments",
                            "maxLength": 4000,
                            "height": 4,
                            "width": 2
                        },
                        {
                            "type": "deleteButton",
                            "name": "Delete"
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
                {
                    "id": "PersonForm",
                    "title": "Person",
                    "url": "/person",
                    "formFieldList": [
                        {
                            "id": "lastName",
                            "type": "text",
                            "name": "LastName",
                            "newRow": true,
                            "required": true,
                            "width": 1
                        },
                        {
                            "id": "firstName",
                            "type": "text",
                            "name": "FirstName",
                            "required": true,
                            "width": 1
                        },
                        {
                            "id": "email",
                            "type": "text",
                            "name": "EMail",
                            "newRow": true,
                            "required": true,
                            "width": 2
                        },
                        {
                            "id": "comments",
                            "type": "text",
                            "name": "Comments",
                            "maxLength": 4000,
                            "height": 4,
                            "width": 2
                        },
                        {
                            "type": "deleteButton",
                            "name": "Delete"
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
                {
                    "id": "MonthlyWorkForm",
                    "title": "Work",
                    "url": "/monthlyWork",
                    "formFieldList": [
                        {
                            "id":   "person",
                            "type": "autocomplete",
                            "name": "Person",
                            "wordSearchEnabled": true,
                            "defaultKey": "personKey",
                            "readonly": true,
                            "form": "PersonForm",
                            "url": "/person",
                            "width": 2,
                            "required": true
                        },
                        {
                            "id": "month",
                            "type": "number",
                            "name": "Month",
                            "width": 1,
                            "required": true,
                            "min": 1,
                            "max": 12,
                            "digits": 0
                        },
                        {
                            "id": "year",
                            "type": "number",
                            "name": "Year",
                            "width": 1,
                            "required": true,
                            "min": 2017,
                            "max": 2199,
                            "digits": 0
                        },
                        {
                            "id": "duration",
                            "type": "number",
                            "name": "Duration",
                            "width": 2,
                            "required": true,
                            "min": 0,
                            "max": 99999,
                            "digits": 1
                        },
                        {
                            "id": "comments",
                            "type": "text",
                            "name": "Comments",
                            "maxLength": 4000,
                            "height": 4,
                            "width": 2
                        },
                        {
                            "type": "deleteButton",
                            "name": "Delete"
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
                            "type": "button",
                            "name": "Projects",
                            "icon": "fa-book",
                            "color": "purple",
                            "page": "projectspage",
                        },
                        {
                            "type": "button",
                            "name": "Tasks",
                            "icon": "fa-tasks",
                            "color": "pumpkin",
                            "page": "taskspage",
                        },
                        {
                            "type": "button",
                            "name": "Persons",
                            "icon": "fa-user",
                            "color": "blue",
                            "page": "personspage",
                        },
                    ]
                },
                {
                    "id": "projectspage",
                    "name": "Projects",
                    "elementList": [
                        {
                            "type": "backbutton",
                        },
                        {
                            "type": "newButton",
                            "name": "NewProject",
                            "icon": "fa-book",
                            "color": "green",
                            "form": {
                                "form": "ProjectForm"
                            }
                        },
                        {
                            "type": "list",
                            "name": "Project List",
                            "icon": "fa-book",
                            "color": "purple",
                            "search": true,
                            "page": "projectpage",
                            "url": "/project"
                        }
                    ]
                },
                {
                    "id": "projectpage",
                    "name": "Project",
                    "elementList": [
                        {
                            "type": "backbutton",
                        },
                        {
                            "type": "button",
                            "name": "EditProject",
                            "icon": "fa-book",
                            "color": "green",
                            "form": {
                                "form": "ProjectForm"
                            }
                        },
                        {
                            "type": "button",
                            "name": "Tasks",
                            "icon": "fa-tasks",
                            "color": "pumpkin",
                            "page": "projecttaskspage",
                        },
                        {
                            "type": "button",
                            "name": "Persons",
                            "icon": "fa-user",
                            "color": "blue",
                            "page": "projectpersonspage",
                        },
                    ]
                },
                {
                    "id": "projecttaskspage",
                    "name": "Tasks",
                    "elementList": [
                        {
                            "type": "backbutton",
                        },
                        {
                            "type": "newButton",
                            "name": "NewTask",
                            "icon": "fa-tasks",
                            "color": "green",
                            "form": {
                                "form": "TaskForm"
                            }
                        },
                        {
                            "type": "list",
                            "name": "Task List",
                            "icon": "fa-tasks",
                            "color": "pumpkin",
                            "search": true,
                            "page": "taskpage",
                            "url": "/project/:projectKey/task"
                        }
                    ]
                },
                {
                    "id": "projectpersonspage",
                    "name": "persons",
                    "elementList": [
                        {
                            "type": "backbutton",
                        },
                        {
                            "type": "newButton",
                            "name": "AddPerson",
                            "icon": "fa-user",
                            "color": "green",
                            "form": {
                                "form": "ProjectPersonForm"
                            }
                        },
                        {
                            "type": "list",
                            "name": "Person List",
                            "icon": "fa-user",
                            "color": "blue",
                            "search": true,
                            "page": "projectpersonpage",
                            "url": "/project/:projectKey/person"
                        }
                    ]
                },
                {
                    "id": "projectpersonpage",
                    "name": "Person",
                    "elementList": [
                        {
                            "type": "backbutton",
                        },
                        {
                            "type": "button",
                            "name": "RemovePerson",
                            "icon": "fa-user",
                            "color": "green",
                            "form": {
                                "form": "ProjectPersonForm"
                            }
                        },
                        {
                            "type": "button",
                            "name": "Projects",
                            "icon": "fa-book",
                            "color": "purple",
                            "page": "personprojectspage",
                        },
                        {
                            "type": "button",
                            "name": "Tasks",
                            "icon": "fa-tasks",
                            "color": "pumpkin",
                            "page": "persontaskspage",
                        },
                    ]
                },
                {
                    "id": "taskspage",
                    "name": "Tasks",
                    "elementList": [
                        {
                            "type": "backbutton",
                        },
                        {
                            "type": "newButton",
                            "name": "NewTask",
                            "icon": "fa-tasks",
                            "color": "green",
                            "form": {
                                "form": "TaskForm"
                            }
                        },
                        {
                            "type": "list",
                            "name": "Task List",
                            "icon": "fa-tasks",
                            "color": "pumpkin",
                            "search": true,
                            "page": "taskpage",
                            "url": "/task"
                        }
                    ]
                },
                {
                    "id": "taskpage",
                    "name": "Task",
                    "elementList": [
                        {
                            "type": "backbutton",
                        },
                        {
                            "type": "button",
                            "name": "EditTask",
                            "icon": "fa-tasks",
                            "color": "green",
                            "form": {
                                "form": "TaskForm"
                            }
                        },
                        {
                            "type": "newButton",
                            "name": "NewWork",
                            "icon": "fa-hand-rock-o",
                            "color": "green",
                            "form": {
                                "form": "WorkForm"
                            }
                        },
                        {
                            "type": "list",
                            "name": "Work List",
                            "icon": "fa-hand-rock-o",
                            "color": "lime",
                            "search": true,
                            "form": {
                                "form": "WorkForm"
                            },
                            "url": "/task/:taskKey/work"
                        }
                    ]
                },
                {
                    "id": "personspage",
                    "name": "persons",
                    "elementList": [
                        {
                            "type": "backbutton",
                        },
                        {
                            "type": "newButton",
                            "name": "NewPerson",
                            "icon": "fa-user",
                            "color": "green",
                            "form": {
                                "form": "PersonForm"
                            }
                        },
                        {
                            "type": "list",
                            "name": "Person List",
                            "icon": "fa-user",
                            "color": "blue",
                            "search": true,
                            "page": "personpage",
                            "url": "/person"
                        }
                    ]
                },
                {
                    "id": "personpage",
                    "name": "Person",
                    "elementList": [
                        {
                            "type": "backbutton",
                        },
                        {
                            "type": "button",
                            "name": "EditPerson",
                            "icon": "fa-user",
                            "color": "green",
                            "form": {
                                "form": "PersonForm"
                            }
                        },
                        {
                            "type": "button",
                            "name": "Projects",
                            "icon": "fa-book",
                            "color": "purple",
                            "page": "personprojectspage",
                        },
                        {
                            "type": "button",
                            "name": "Tasks",
                            "icon": "fa-tasks",
                            "color": "pumpkin",
                            "page": "persontaskspage",
                        },
                        {
                            "type": "button",
                            "name": "Workload",
                            "icon": "fa-hand-rock-o",
                            "color": "lime",
                            "page": "personworkpage",
                        },
                    ]
                },
                {
                    "id": "persontaskspage",
                    "name": "Tasks",
                    "elementList": [
                        {
                            "type": "backbutton",
                        },
                        {
                            "type": "newButton",
                            "name": "NewTask",
                            "icon": "fa-tasks",
                            "color": "green",
                            "form": {
                                "form": "TaskForm"
                            }
                        },
                        {
                            "type": "list",
                            "name": "Task List",
                            "icon": "fa-tasks",
                            "color": "pumpkin",
                            "search": true,
                            "page": "taskpage",
                            "url": "/person/:personKey/task"
                        }
                    ]
                },
                {
                    "id": "personprojectspage",
                    "name": "Project",
                    "elementList": [
                        {
                            "type": "backbutton",
                        },
                        {
                            "type": "button",
                            "name": "EditProject",
                            "icon": "fa-book",
                            "color": "green",
                            "form": {
                                "form": "ProjectForm"
                            }
                        },
                        {
                            "type": "list",
                            "name": "Project List",
                            "icon": "fa-book",
                            "color": "purple",
                            "search": true,
                            "page": "projectpage",
                            "url": "/person/:personKey/project"
                        }
                    ]
                },
                {
                    "id": "personworkpage",
                    "name": "Project",
                    "elementList": [
                        {
                            "type": "backbutton",
                        },
                        {
                            "type": "newButton",
                            "name": "NewWorkload",
                            "icon": "fa-hand-rock-o",
                            "color": "green",
                            "form": {
                                "form": "MonthlyWorkForm"
                            }
                        },
                        {
                            "type": "list",
                            "name": "Work List",
                            "icon": "fa-hand-rock-o",
                            "color": "lime",
                            "search": true,
                            "form": {
                                "form": "MonthlyWorkForm"
                            },
                            "url": "/monthlyWork"
                        }
                    ]
                },
            ]
        }
    };


    get guiModel() {
        return this._guiModel;
    }
}