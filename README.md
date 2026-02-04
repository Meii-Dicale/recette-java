PROJET SPRING
BOOT
CDA 2025 - 2027
Theo Bialasik
04/02/2026
Table des matières
Objectifs du formateur ........................................................................................................... 2
Tables ................................................................................................................................... 2
User ................................................................................................................................... 2
Recette .............................................................................................................................. 2
Ingrédient ........................................................................................................................... 2
Liaison ................................................................................................................................... 2
Contraintes ............................................................................................................................ 2
Contraintes techniques .......................................................................................................... 3
Objectifs du formateur
• CRUD pour les tables de base de données (MySQL, PostgreSQL)
• Gestion d’un TOKEN
• Gestion de rôle avec des accès contrôlée
• GIT (avec plusieurs commit)
• Génération de PDF ou XLSX
Tables
User
• Id
• Nom
• Prenom
• Role
• Password
• Telephone
• Mail
Recette
• Id
• Nom du plat
• Durée de préparation
• Durée de cuisson
• Nombre calorique
• Partage
Ingrédient
• Id
• Libelle
• Type (enum)
• Nombre de calorie
Liaison
• User a des recettes favorites
• User a des recettes persos (FK_USER dans recette)
• Recette sont constitué d’ingrédients (quantité par recette)
Contraintes
• On peut partager ou non une recette
• Recette sous forme PDF/XLSX
Contraintes techniques
• Avoir une architecture :
o Config
o Controller
o Dtos
o Entites
o Mappers
o Repositorie
o Services



Lancer le serveur :
appuyer sur run 