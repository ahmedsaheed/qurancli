# Generated with JReleaser 1.0.0 at 2022-06-29T04:00:11.300261+01:00
class QuranCLI < Formula
  desc "A simple tool to Read, Search and Recite the Quran."
  homepage "https://github.com/ahmedsaheed/quranCLI"
  url "https://github.com/ahmedsaheed/quranCLI/releases/download/v1.0.0/quranCLI-1.0.zip"
  version "1.0"
  sha256 "5e98c57f3fa4f6681308ee1359a6e79bcf6ea9794865a5eead831a2e3e25a702"
  license "Apache-2.0"

  depends_on "openjdk@17"

  def install
    libexec.install Dir["*"]
    bin.install_symlink "#{libexec}/bin/quranCLI" => "quranCLI"
  end

  test do
    output = shell_output("#{bin}/quranCLI --version")
    assert_match "1.0", output
  end
end
