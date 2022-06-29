# Generated with JReleaser 1.0.0 at 2022-06-29T01:00:12.568142+01:00
class App < Formula
  desc "A simple tool to Read, Search and Recite the Quran."
  homepage "https://github.com/ahmedsaheed/quranCLI"
  url "https://github.com/ahmedsaheed/quranCLI/releases/download/v1.0.0/quranCLI-1.0.zip"
  version "1.0"
  sha256 "9fca8e95c11aad7f9392025c864b4fa3caace4347aa3ce0c742b492067c0eed9"
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
