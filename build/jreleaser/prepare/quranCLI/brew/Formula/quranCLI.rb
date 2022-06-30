# Generated with JReleaser 1.0.0 at 2022-06-30T17:26:21.913003+01:00
class Qurancli < Formula
  desc "A simple tool to Read, Search and Recite the Quran."
  homepage "https://github.com/ahmedsaheed/quranCLI"
  url "https://github.com/ahmedsaheed/quranCLI/releases/download/v1.0.0/qurancli-1.0.zip"
  version "1.0"
  sha256 "b8441214642053462d2df4776739329e804a9c91c7f41d6c1858463df4c8a3ab"
  license "Apache-2.0"

  depends_on "openjdk@17"

  def install
    libexec.install Dir["*"]
    bin.install_symlink "#{libexec}/bin/qurancli" => "qurancli"
  end

  test do
    output = shell_output("#{bin}/qurancli --version")
    assert_match "1.0", output
  end
end
