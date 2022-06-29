# Generated with JReleaser 1.0.0 at 2022-06-29T06:43:14.844242+01:00
class Qurancli < Formula
  desc "A simple tool to Read, Search and Recite the Quran."
  homepage "https://github.com/ahmedsaheed/quranCLI"
  url "https://github.com/ahmedsaheed/quranCLI/releases/download/v1.0.0/quranCLI-1.0.zip"
  version "1.0"
  sha256 "0826525c9cf74f6555a6f06125b2960d35fdb4f2a3ae4bcb3bdd2ad80e55d7ff"
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
