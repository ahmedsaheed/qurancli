# Generated with JReleaser 1.0.0 at 2022-06-28T16:11:44.124288+01:00
class App < Formula
  desc "A simple tool to Read, Search and Recite the Quran."
  homepage "https://github.com/ahmedsaheed/quranCLI"
  url "https://github.com/ahmedsaheed/quranCLI/releases/download/early-access/quranCLI-1.0-SNAPSHOT.zip"
  version "1.0-SNAPSHOT"
  sha256 "6de429b313feebfd323055bdb05da3d2806281690d4d8f73ab116ada79630f3a"
  license "Apache-2.0"

  depends_on :key1 => "value1"
  depends_on "key2" => "value2"
  depends_on :key3
  depends_on "key4"
  depends_on "openjdk@17"

  def install
    libexec.install Dir["*"]
    bin.install_symlink "#{libexec}/bin/quranCLI" => "quranCLI"
  end

  test do
    output = shell_output("#{bin}/quranCLI --version")
    assert_match "1.0-SNAPSHOT", output
  end
end
