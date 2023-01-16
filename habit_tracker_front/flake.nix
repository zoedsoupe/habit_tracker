{
  inputs.nixpkgs.url = "github:NixOS/nixpkgs/nixos-22.11";

  outputs = { self, nixpkgs }:
    let
      system = "x86_64-linux";

      pkgs = import nixpkgs {
        inherit system;
        config.allowUnfree = true;
      };
    in
    rec {
      devShells."${system}".default = with pkgs; mkShell {
        name = "habit_tracker";
        buildInputs = with elmPackages; [
          elm
          elm-format
          nodejs
        ];
      };
    };
}
