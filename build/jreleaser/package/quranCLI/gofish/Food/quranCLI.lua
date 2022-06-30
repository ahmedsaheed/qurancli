-- Generated with JReleaser 1.0.0 at 2022-06-29T23:41:18.624752+01:00

local name = "quranCLI"
local version = "1.0"

food = {
    name = name,
    description = "A simple tool to Read, Search and Recite the Quran.",
    license = "Apache-2.0",
    homepage = "https://github.com/ahmedsaheed/quranCLI",
    version = version,
    packages = {
        {
            os = "darwin",
            arch = "amd64",
            url = "https://github.com/ahmedsaheed/" .. name .. "/releases/download/v" .. version .. ".0/" .. name .. "-" .. version .. ".zip",
            sha256 = "a7169607841522940f4b30d5d3094162b52402d211026e2d2fba943e4d877ea7",
            resources = {
                {
                    path = name .. "-" .. version .. "/bin/" .. name,
                    installpath = "bin/" .. name,
                    executable = true
                }
            }
        },
        {
            os = "darwin",
            arch = "arm64",
            url = "https://github.com/ahmedsaheed/" .. name .. "/releases/download/v" .. version .. ".0/" .. name .. "-" .. version .. ".zip",
            sha256 = "a7169607841522940f4b30d5d3094162b52402d211026e2d2fba943e4d877ea7",
            resources = {
                {
                    path = name .. "-" .. version .. "/bin/" .. name,
                    installpath = "bin/" .. name,
                    executable = true
                }
            }
        },
        {
            os = "linux",
            arch = "amd64",
            url = "https://github.com/ahmedsaheed/" .. name .. "/releases/download/v" .. version .. ".0/" .. name .. "-" .. version .. ".zip",
            sha256 = "a7169607841522940f4b30d5d3094162b52402d211026e2d2fba943e4d877ea7",
            resources = {
                {
                    path = name .. "-" .. version .. "/bin/" .. name,
                    installpath = "bin/" .. name,
                    executable = true
                }
            }
        },
        {
            os = "linux",
            arch = "arm64",
            url = "https://github.com/ahmedsaheed/" .. name .. "/releases/download/v" .. version .. ".0/" .. name .. "-" .. version .. ".zip",
            sha256 = "a7169607841522940f4b30d5d3094162b52402d211026e2d2fba943e4d877ea7",
            resources = {
                {
                    path = name .. "-" .. version .. "/bin/" .. name,
                    installpath = "bin/" .. name,
                    executable = true
                }
            }
        },
        {
            os = "windows",
            arch = "amd64",
            url = "https://github.com/ahmedsaheed/" .. name .. "/releases/download/v" .. version .. ".0/" .. name .. "-" .. version .. ".zip",
            sha256 = "a7169607841522940f4b30d5d3094162b52402d211026e2d2fba943e4d877ea7",
            resources = {
                {
                    path = name .. "-" .. version .. "\\bin\\" .. name .. ".bat",
                    installpath = "bin\\" .. name .. ".bat"
                    
                }
            }
        },
        {
            os = "windows",
            arch = "arm64",
            url = "https://github.com/ahmedsaheed/" .. name .. "/releases/download/v" .. version .. ".0/" .. name .. "-" .. version .. ".zip",
            sha256 = "a7169607841522940f4b30d5d3094162b52402d211026e2d2fba943e4d877ea7",
            resources = {
                {
                    path = name .. "-" .. version .. "\\bin\\" .. name .. ".bat",
                    installpath = "bin\\" .. name .. ".bat"
                    
                }
            }
        },
    }
}
