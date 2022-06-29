# Generated with JReleaser 1.0.0 at 2022-06-29T07:15:37.909763+01:00
class Qurancli < Formula
  desc "A simple tool to Read, Search and Recite the Quran."
  homepage "https://github.com/ahmedsaheed/quranCLI"
  url "https://github.com/ahmedsaheed/quranCLI/releases/download/v1.0.0/quranCLI-1.0.zip"
  version "1.0"
  sha256 "a7169607841522940f4b30d5d3094162b52402d211026e2d2fba943e4d877ea7"
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
