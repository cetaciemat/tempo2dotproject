# README

**tempo2dotproject** is a Command Line Interface (CLI) tool for exporting the worklogs of [Tempo](http://www.tempoplugin.com/) to [DotProject](http://sourceforge.net/projects/dotproject/).

## Main features:
* tempo2dotproject can export from Tempo to DotProject those logs that fit into a time range.
* You can export just the logs associated to specific tasks.
* You can export just the logs associated specific users.
* tempo2dotproject provides a *test mode* to make tests without dumping data to DotProject. In other words, you will see what changes will take place.

## Compatible versions
This software has been tested using JIRA v5.2.5, DotProject 2.1.6 and Tempo 7.7.1.2. In future implementations we expect to test it using more recent versions of those tools.

## Installation
You need to have JRE6 installed in your computer in order to execute tempo2dotproject. Also, in order to build tempo2dotproject, you need [ANT](http://ant.apache.org/).

In order to build tempo2dotproject you need to clone this repository and execute on it:

``ant clean dist``

In the ``dist`` folder, Ant script will generate a tar.gz file which you can uncompress in the location you want to put the application.

## Configuration

In order to use tempo2dotproject, you have to complete the following fields in the ``config.properties`` file:

###dotproject.db.url
The information of the database as a JDBC url:
``jdbc:mysql://<server_hostname>/<database_name>``

###dotproject.db.user
The user that will access to the DotProject database. 

###dotproject.db.password
The user password.

###dotproject.db.encoding
It is needed to specify the encoding of the DotProject database (generally *UTF-8* or *ISO-8859-1*).

###jira.api.url
The base URL of the source JIRA installation.

###jira.api.user
The name of the JIRA user.

###jira.api.password
Password of the JIRA user.

###jira.dotproject.field
In order to specify where to export the Tempo logs, you have to create a custom field of  type "URL" in JIRA frontend. The code of this field has the following format:

``customfield_<id>``

E.g.:

`customfield_10301`

In order to get the `<id>` part you can see `http://<your_jira_url>/secure/admin/ViewCustomFields.jspa`. Once there search your custom field and click in *Configuration* link. Last characters of the should end in something similar to `?customFieldId=10301`. This is the number ID of your custom field.

That field **must** be defined in a task for export its logs, and have to contain the URL of the task where the logs will be exported. I.e. `http://<your_dotproject>/index.php?m=tasks&a=view&task_id=4731`


###tempo.api.key
This refers to the Tempo API Key. Here you can read how to generate this key: [https://tempoplugin.jira.com/wiki/display/TEMPO/Access+Control](https://tempoplugin.jira.com/wiki/display/TEMPO/Access+Control)

## How to use

You can execute tempo2project as a normal JAR file:

`java -jar tempo2dotproject.jar start-date end-date users <keys> <--test-mode>`

### Parameters

* `start-date`: This parameter is mandatory. It is the start date of the time range to be used for exporting the logs. Expected format: `yyyy-mm-dd`
* `end-date`: This parameter is mandatory. Is the end date of the time range to be used for exporting the logs. Expected format: `yyyy-mm-dd`
* `users`: This parameter is mandatory. It is used for specifying the users whose logs will be exported. Expected format: `username1,username2,username3`
* `keys`: This parameter is optional. It specifies the projects name which logs will be exported. Expected format: `PROJECT-1,PROJECT-2,PROJECT-3`
* `--test-mode`: This parameter is optional. It disables writings to DotProject database. It is useful for testing purposes, such as checking the configuration.

###LICENSE

The MIT License (MIT)

Copyright (c) 2014 CETA-Ciemat

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
