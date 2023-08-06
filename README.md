<h1 align="center">Quran CLI</h1>
<h3 align="center">Read, Search and Recite the Quran from your terminal.</h3>
<p align="center">
 <a  href="https://www.apache.org/licenses/LICENSE-2.0">
    <img src="https://img.shields.io/hexpm/l/apa">
  </a>
   <img src="https://img.shields.io/homebrew/v/quran">
  </p>
  
https://github.com/ahmedsaheed/qurancli/assets/87912847/df42a8ad-d0c0-44bc-83c6-cfb9f355a66f

## Examples
```bash
qurancli -s 1 # Read surah Al-Fatiha in arabic
qurancli -s 1 -t # Read surah Al-Fatiha in english
qurancli -s 1 -a # Play Al-Fatiha Audio
qurancli -s 1 -at # Play Al-Fatiha Audio in english
qurancli -q "Mohammed" # Search any word in the quran
```


## Commands

|Command|Usage|
|-|-|
|-s, --surah-number=surahNumber|find a surah by it's number in range 1 -> 114|
|-a, --audio| Plays audio version of surah|
|-at, --AudioTranslation| Translate surah to english and play audio|
|-q, --query=queryString| Search a keyword from the quran|
|-t, --translation|Translate a selected surah to english|
|-V, --version|Print version information and exit.|

## Install

###

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

<sub>Copyright(c) quranCLI 2022</sub>

<sub>This application requires a terminal which support bi-directional text<sub> 
