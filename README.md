<h1 align="center">Quran CLI</h1>
<p align="center">
    <img width="200" src="https://user-images.githubusercontent.com/87912847/176374730-a7782c3b-2e3c-485f-baa8-a8f93887affb.png" alt="logo">
</p>
<h3 align="center">Read, Search and Recite the Quran.</h3>
<p align="center">
 <a  href="https://www.apache.org/licenses/LICENSE-2.0">
    <img src="https://img.shields.io/hexpm/l/apa">
  </a>
  
   <img src="https://img.shields.io/homebrew/v/quran">
  
  
  </p>



https://user-images.githubusercontent.com/87912847/176378236-c3bcd52c-c6c4-4cf2-aaf4-9a1a2ed9c7b9.mov

> Unmute the video to listen

# Table of Contents
- [About](#about)
- [Examples](#examples)
- [Commands](#commands)
- [Install](#install)
- [Build](#build)


## About

📖 __quranCLI__ is simple tool to Read, Search and Recite the Quran from cli.

written in java using [picocli](https://github.com/remkop/picocli)

⚙️ Fast and with almost zero space consumption.

🦎 Works on all OS no config required- Mac, Linux & Windows

🍿 This app is inspired by __mangal__ [mangal](https://github.com/metafates/mangal). Check it out!


## Examples
```bash
# Read surah Al-Fatiha in arabic
qurancli -s 1

# Read surah Al-Fatiha in english
qurancli -s 1 -t

# Play Al-Fatiha Audio
qurancli -s 1 -a

# Play Al-Fatiha Audio in english
qurancli -s 1 -at

# Search any word in the quran
qurancli -q "Mohammed"
```


## Commands

```
Usage: quranCLI  [-ahtV] [-at] [-q=<query>] [-s=<surahNumber>]
      ______ ______
    _/      Q      \_
   // ~~ ~~ | ~~ ~  \\
  // ~ ~ ~~ | ~~~ ~~ \\
 //________.|.________\\
`----------`-'----------'
       AL-QURAN CLI
A simple tool to Read, Search and Recite the Quran.

  -a, --audio           plays audio version of surah
      -at, --AudioTranslation
                        Translate surah to english and play audio
  -h, --help            Show this help message and exit.
  -q, --query=<query>   Search a keyword in the quran
  -s, --surah-number=<surahNumber>
                        find a surah by it's number in range 1..114
  -t, --translation     Translate surah to english
  -V, --version         Print version information and exit.

Copyright(c) quranCLI 2022

```
## Install

### ⚠️[This application requires a terminal which support bi-directional text](https://gist.github.com/XVilka/a0e49e1c65370ba11c17)⚠️
### MacOS and Linux

Install using [Homebrew](https://brew.sh/)

```bash
brew tap ahmedsaheed/qurancli
brew install qurancli

# or

brew install ahmedsaheed/tap/qurancli
```

<details>
<summary>Update & Uninstall</summary>

#### Update

```bash
brew upgrade qurancli
```

#### Uninstall

```bash
brew uninstall qurancli -f
```

</details>

### Windows 
Install Using [Scoop](https://scoop.sh/#/)

Step 1: Install Java & Specify the JAVA_HOME path

```bash
scoop bucket add java
scoop install openjdk

# AND

scoop bucket add extras
scoop install find-java

```
Step 2: Install the application

```bash
scoop bucket add ahmedsaheed https://github.com/ahmedsaheed/scoop-ahmedsaheed.git
scoop install qurancli
```

## Build
```bash
git clone https://github.com/ahmedsaheed/quranCL

cd quranCLI

java -jar /path/to/quranCLI-1.0.jar [args]

#Or
#On unix-based operating systems, you can define an alias in bashrc. For example:

alias qurancli='java -cp "/path/to/quranCLI-1.0.jar" qurancli'

qurancli -s -1

```

## :question: Get Help

There are few ways to get help:

 1. You can open issues with questions in this repository.
 2. For bug reports and feature requests, open issues. :bug:

## :yum: How to contribute
Have an idea? Found a bug? Open an Issue.
