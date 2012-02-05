# Creating Your Description File

In this section I'll describe the .json file you have to write. You can find a detailed example [here](https://raw.github.com/Lifty/lifty/master/lifty-recipe/lifty.json) or check out the case classes that I use to parse the json file [here]().

## Top level

At the top level you have an object the the properties

- **origin**: The url of where the .json file is coming from. This is needed so lifty can update recipes by checking if a newer version is available.
- **version**: The current version of the recipe
- **sources**: An array of object that describe the source files (i.e. template files)
- **arguments**: An array of objects defining the arguments that your templates might need. More on this later.
- **templates**: An array of objects defining the templates that Lifty can create. More on this later.

Example:

	{
		"origin"  : "url of where the recipe is stored",
		"version" : 1.3,
		"sources" : [ ... ],
		"arguments" : [ ... ],
		"templates" : [ ... ]
	}

## sources

As explained, this has to be an array of objects. Each object needs to have the following properties:

- **name**: The name you want the template source file to have once downloaded.
- **url**: The url of the templates. Lifty uses this to download the template file.

## arguments

As explained, this has to be an array of objects. Each object needs to have the following properties:

- **name**: The name of the argument. This has to be unique.
- **descriptiveName**: Some descriptive name of the argument. This is used when Lifty needs to query the user about a value for the argument.
- **default**[Optional]: If you want to provide a default value then you can do it here. This string can use the value of other arguments by using this syntax "\${mainpack}.application". Here the value of the argument with name mainpack would be substituted for \${mainpack}

## templates

As explained, this has to be an array of objects. Each object represents something that Lifty can create. Each template can render many source templates if needed (i.e. if you wanted to create an entire project for example). Each object needs to have the following properties:

- **name**: The name of the template, this has to be unique.
- **description**: Some description of the things that will created by this template. This will be used when invoking `lifty yourRecipe templates`
- **notice**[Optional]: Some message that you want to show the user after the template has been processed. Might be some information explaining how the user should proceed.
- **folders**[Optional]: an array of strings of any folders that you want to create. Use unix style paths (Lifty will translate them to windows paths if needed).
- **arguments**: A list of string. The strings should be the names of the arguments that this template needs. Place the arguments in the order you want to query for them. The order is important if some of the arguments depends on any of the others.
- **files**: A list of objects. The objects needs to have the properties **file** and **destination** where file is the name of the template (the name you gave it in the sources list) and destination is a path that tells lifty where to place the generated file. The destination can also use the \${..} syntax to use argument values.
- **injections**[Optional]: A list of objects with the properties: file, into, point. file is the name of the source file you want to inject into some other source file. into is the name of the source file you want to inject into. point is the injection point in that file. More about those in the `creating your templates` section.
- **dependencies**[Optional]: A list of other templates that this template depends on, that is, this template wants that template to be processed before this one and potentially inject something into the source files.