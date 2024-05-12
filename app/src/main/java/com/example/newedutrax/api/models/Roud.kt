package com.example.newedutrax.api.models



data class Roud(val icon: Int?,
    val title:String,
    val description: String) {
    companion object {
        val round: HashMap<String, List<Roud>> = HashMap()
        fun initData(){
            round["frontEnd"] = getFrontEndList()
            round["backEnd"] = getBackEndList()
            round["fullStack"] = getFullStackList()
            round["cyberSecurity"] = getCyberSecurityList()
        }

        fun getFrontEndList(): List<Roud> {
            return listOf(
                Roud(
                    title = "HTML",
                    description = "To start your career in the Web field, you need to learn the language of web page design, which is essential whether you are a designer or a developer. This language is HTML, and you will find the educational course along with all the references you need, as well as exercises and many ideas.",
                    icon = null
                ),
                Roud(
                    title = "CSS",
                    description = "It is used to style and layout web pages. With CSS, you can control the color, font, size, spacing, and various other design elements on your web page. CSS allows users to separate the layout and design from the HTML structure, making it easier to change the appearance of web pages consistently.",
                    icon = null
                ),
                Roud(
                    title = "BootStrap",
                    description = "Bootstrap is the most famous framework for website design, as many programmers use it to design their websites because it saves a lot of effort and trouble, as website development in its modern form no longer requires rewriting every movement and every element of the website pages, and organizing the page has become a simple matter.",
                    icon = null
                ),
                Roud(
                    title = "JavaScript",
                    description = "It is considered your most important step in Front End, and it is considered the first programming language you will deal with. You must be familiar with  ( Basics - DOM - BOM - OOP - API )",
                    icon = null
                ),
                Roud(
                    null,
                    "JS Framework",
                    "To make it easier for you and to provide the best experience in the shortest time, you should use a JavaScript framework. You can choose between many of them, Such as ( Vue.js - React.js - Angular.js )"
                )
            )
        }

        fun getBackEndList(): List<Roud> {
            return listOf(
                Roud(
                    title = "Programming Language",
                    description = "In this step, you can choose any programming language that supports working with the web, and there are many examples nodeJs - DotNet.",
                    icon = null
                ),
                Roud(
                    title = "Framework",
                    description = "After choosing the programming language that you will work with in Backend, the next step for you is to choose a work environment to make it easier for you and provide the best code in the shortest possible time. Every programming language has the appropriate work environment, so if you choose PHP, the appropriate work environment is Laravel.",
                    icon = null
                ),
                Roud(
                    title = "My SQL",
                    description = "After learning all this, you must now deal with databases to save your project data in. MySQL databases are among the most famous databases in the world of the web, and they are responsible for dealing with the database of your application or project and sending and receiving data from it",
                    icon = null
                ),
                Roud(
                    title = "JavaScript",
                    description = "It is considered your most important step in Front End, and it is considered the first programming language you will deal with. You must be familiar with  ( Basics - DOM - BOM - OOP - API )",
                    icon = null
                ),
                Roud(
                    null,
                    "Publish your site",
                    "After you have completed the website, you must now publish it on the World Wide Web so that all users can see it. To make the website files visible to the public, you must upload them to a hosting and link the hosting to a domain such as www.EDUTRAX.net, for example."
                )
            )
        }

        fun getFullStackList(): List<Roud> {
            return listOf(
                Roud(
                    title = "Programming Language",
                    description = "In this step, you can choose any programming language that supports working with the web, and there are many examples nodeJs - DotNet.",
                    icon = null
                ),
                Roud(
                    title = "UI/UX",
                    description = "Learning UI UX will make you able to provide the best user experience in terms of design.",
                    icon = null
                ),
                Roud(
                    title = "HTML",
                    description = "To start your career in the Web field, you need to learn the language of web page design, which is essential whether you are a designer or a developer. This language is HTML, and you will find the educational course along with all the references you need, as well as exercises and many ideas.",
                    icon = null
                ),
                Roud(
                    title = "CSS",
                    description = "It is used to style and layout web pages. With CSS, you can control the color, font, size, spacing, and various other design elements on your web page. CSS allows users to separate the layout and design from the HTML structure, making it easier to change the appearance of web pages consistently.",
                    icon = null
                ),
                Roud(
                    title = "BootStrap",
                    description = "Bootstrap is the most famous framework for website design, as many programmers use it to design their websites because it saves a lot of effort and trouble, as website development in its modern form no longer requires rewriting every movement and every element of the website pages, and organizing the page has become a simple matter.",
                    icon = null
                ),
                Roud(
                    title = "JavaScript",
                    description = "It is considered your most important step in Front End, and it is considered the first programming language you will deal with. You must be familiar with  ( Basics - DOM - BOM - OOP - API )",
                    icon = null
                ),
                Roud(
                    title = "JavaScript",
                    description = "It is considered your most important step in Front End, and it is considered the first programming language you will deal with. You must be familiar with  ( Basics - DOM - BOM - OOP - API )",
                    icon = null
                ),
                Roud(
                    title = "Framework",
                    description = "After choosing the programming language that you will work with in Backend, the next step for you is to choose a work environment to make it easier for you and provide the best code in the shortest possible time. Every programming language has the appropriate work environment, so if you choose PHP, the appropriate work environment is Laravel.",
                    icon = null
                ),
                Roud(
                    title = "My SQL",
                    description = "After learning all this, you must now deal with databases to save your project data in. MySQL databases are among the most famous databases in the world of the web, and they are responsible for dealing with the database of your application or project and sending and receiving data from it",
                    icon = null
                ),
                Roud(
                    null,
                    "Publish your site",
                    "After you have completed the website, you must now publish it on the World Wide Web so that all users can see it. To make the website files visible to the public, you must upload them to a hosting and link the hosting to a domain such as www.EDUTRAX.net, for example."
                )
            )
        }

        fun getCyberSecurityList(): List<Roud> {
            return listOf(
                Roud(
                    title = "Programming",
                    description = "You must learn many programming languages, but do not worry, you do not have to master them all. It is enough to know how to write each language The Python language is one of the most important programming languages in demand in this field But the advice of mastering several programming languages will allow you to excel in the field of cyber security.",
                    icon = null
                ),
                Roud(
                    title = "Networking",
                    description = "Learning about networks is one of the most weak points in any system and is considered one of the most important things that a cyber security specialist must know, as most electronic attacks occur through different networks.",
                    icon = null
                ),
                Roud(
                    title = "Operation Systems",
                    description = "Operating systems like Windows, Linux, Android, etc. are responsible for regulating hardware functions, so they are the first to be hacked ,Your task is to ensure that the private devices in the system you are protecting do not get hacked, in addition to knowing how to secure it and the operating systems it uses against any attacks.You should know that Linux is one of the most important systems to learn, because it contains a lot of important and useful software in this field, and it is more difficult to hack than other operating systems.",
                    icon = null
                ),
                Roud(
                    title = "IT",
                    description = "Information Technology is one of the most important skills that must be learned in the field of cyber security.",
                    icon = null
                ),
                Roud(
                    null,
                    "Computer Science",
                    "Computer science is indispensable whether for learning cybersecurity or other technical fields, as it is essential for understanding how things are done."
                )
            )
        }
    }
}