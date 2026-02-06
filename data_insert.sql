-- Script d'insertion de données de test pour la base de données PostgreSQL
-- Base de données: mes_recettes

-- Nettoyage des tables (optionnel, à commenter si vous voulez garder les données existantes)
-- Décommentez les lignes suivantes si vous voulez vider les tables avant l'insertion
TRUNCATE TABLE user_recettes_favorites CASCADE;
 TRUNCATE TABLE ingredient_par_recette CASCADE;
 TRUNCATE TABLE recettes CASCADE;
 TRUNCATE TABLE ingredients CASCADE;
 TRUNCATE TABLE users CASCADE;
-- Réinitialiser les séquences après TRUNCATE
ALTER SEQUENCE users_id_seq RESTART WITH 1;
ALTER SEQUENCE ingredients_id_seq RESTART WITH 1;
ALTER SEQUENCE recettes_id_seq RESTART WITH 1;
 ALTER SEQUENCE ingredient_par_recette_id_seq RESTART WITH 1;

-- Insertion des utilisateurs
-- Les mots de passe sont hashés avec BCrypt (tous les mots de passe = "password123")
-- Hash BCrypt pour "password123": $2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy
-- IMPORTANT: Si vous avez déjà des utilisateurs, vérifiez leurs IDs avant d'insérer les recettes
INSERT INTO users (nom, prenom, role, password, telephone, mail) VALUES
('Dupont', 'Jean', 'ADMIN', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0612345678', 'jean.dupont@email.com'),
('Martin', 'Marie', 'USER', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0698765432', 'marie.martin@email.com'),
('Bernard', 'Pierre', 'USER', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0654321098', 'pierre.bernard@email.com'),
('Dubois', 'Sophie', 'USER', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0678901234', 'sophie.dubois@email.com'),
('Lefebvre', 'Thomas', 'ADMIN', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0611223344', 'thomas.lefebvre@email.com');

-- Insertion des ingrédients
-- Type enum: 0=VIANDE, 1=FRUIT, 2=LEGUME, 3=EPICE, 4=BOISSON, 5=AUTRE
INSERT INTO ingredients (libelle, type, nombrecalorique) VALUES
-- Viandes (type = 0)
('Poulet', 0, 165),
('Boeuf', 0, 250),
('Porc', 0, 242),
('Saumon', 0, 208),
('Thon', 0, 144),
-- Fruits (type = 1)
('Tomate', 1, 18),
('Avocat', 1, 160),
('Citron', 1, 29),
('Pomme', 1, 52),
('Banane', 1, 89),
-- Légumes (type = 2)
('Carotte', 2, 41),
('Oignon', 2, 40),
('Ail', 2, 149),
('Poivron', 2, 31),
('Courgette', 2, 17),
('Epinards', 2, 23),
('Brocoli', 2, 34),
-- Épices (type = 3)
('Sel', 3, 0),
('Poivre', 3, 251),
('Curry', 3, 325),
('Paprika', 3, 282),
('Cumin', 3, 375),
('Cannelle', 3, 247),
-- Boissons (type = 4)
('Vin blanc', 4, 82),
('Bouillon de legumes', 4, 5),
('Lait', 4, 42),
-- Autres (type = 5)
('Oeufs', 5, 155),
('Fromage rape', 5, 363),
('Beurre', 5, 717),
('Huile d''olive', 5, 884),
('Farine', 5, 364),
('Riz', 5, 130),
('Pates', 5, 131);

-- Insertion des recettes
-- IMPORTANT: Si vous avez déjà des users dans la table, exécutez d'abord cette requête pour voir leurs IDs:
-- SELECT id, nom, prenom, mail FROM users ORDER BY id;
-- Puis ajustez les user_id ci-dessous en conséquence, ou videz les tables avec TRUNCATE (voir en haut du script)
INSERT INTO recettes (nom, dureepreparation, dureecuisson, nombrecalorique, partage, user_id) VALUES
('Poulet roti aux herbes', 15, 60, 320, true, 1),
('Salade Cesar', 20, 0, 280, true, 2),
('Pates carbonara', 10, 15, 450, true, 3),
('Saumon grille aux legumes', 20, 25, 380, false, 2),
('Risotto aux champignons', 15, 30, 420, true, 4),
('Tarte aux pommes', 30, 45, 350, true, 1),
('Curry de legumes', 15, 25, 290, true, 3),
('Omelette aux epinards', 5, 10, 220, false, 4),
('Lasagnes bolognaise', 45, 60, 520, true, 5),
('Soupe de legumes', 15, 30, 150, true, 2);

-- Insertion des relations ingrédients par recette
-- MAPPING DES IDs: 1=Poulet, 2=Boeuf, 3=Porc, 4=Saumon, 5=Thon, 6=Tomate, 7=Avocat, 8=Citron, 9=Pomme, 10=Banane
-- 11=Carotte, 12=Oignon, 13=Ail, 14=Poivron, 15=Courgette, 16=Epinards, 17=Brocoli
-- 18=Sel, 19=Poivre, 20=Curry, 21=Paprika, 22=Cumin, 23=Cannelle
-- 24=Vin blanc, 25=Bouillon, 26=Lait, 27=Oeufs, 28=Fromage, 29=Beurre, 30=Huile, 31=Farine, 32=Riz, 33=Pates

-- Recette 1: Poulet roti aux herbes
INSERT INTO ingredient_par_recette (recette_id, ingredient_id, quantite) VALUES
(1, 1, 1),   -- 1 poulet
(1, 11, 2),  -- 2 carottes
(1, 12, 1),  -- 1 oignon
(1, 13, 3),  -- 3 gousses d'ail
(1, 18, 1),  -- Sel
(1, 19, 1),  -- Poivre
(1, 20, 1),  -- Curry

-- Recette 2: Salade Cesar
(2, 1, 200), -- 200g de poulet
(2, 6, 3),   -- 3 tomates
(2, 27, 2),  -- 2 oeufs
(2, 28, 50), -- 50g fromage
(2, 18, 1),  -- Sel
(2, 19, 1),  -- Poivre

-- Recette 3: Pates carbonara
(3, 33, 400), -- 400g pates
(3, 27, 4),   -- 4 oeufs
(3, 28, 100), -- 100g fromage
(3, 3, 200),  -- 200g porc (lardons)
(3, 18, 1),   -- Sel
(3, 19, 1),   -- Poivre

-- Recette 4: Saumon grille aux legumes
(4, 4, 1),    -- 1 saumon
(4, 14, 2),   -- 2 poivrons
(4, 15, 2),   -- 2 courgettes
(4, 11, 3),   -- 3 carottes
(4, 18, 1),   -- Sel
(4, 19, 1),   -- Poivre
(4, 21, 1),   -- Paprika

-- Recette 5: Risotto aux champignons
(5, 32, 300), -- 300g riz
(5, 12, 1),   -- 1 oignon
(5, 13, 2),   -- 2 gousses d'ail
(5, 25, 1),   -- Bouillon
(5, 28, 50),  -- 50g fromage
(5, 18, 1),   -- Sel
(5, 19, 1),   -- Poivre

-- Recette 6: Tarte aux pommes
(6, 9, 6),    -- 6 pommes
(6, 31, 250), -- 250g farine
(6, 29, 100), -- 100g beurre
(6, 23, 1),   -- Cannelle
(6, 27, 1),   -- 1 oeuf

-- Recette 7: Curry de legumes
(7, 11, 3),   -- 3 carottes
(7, 14, 2),   -- 2 poivrons
(7, 15, 2),   -- 2 courgettes
(7, 12, 1),   -- 1 oignon
(7, 20, 2),   -- 2 cuillères curry
(7, 18, 1),   -- Sel

-- Recette 8: Omelette aux epinards
(8, 27, 3),   -- 3 oeufs
(8, 16, 200), -- 200g epinards
(8, 18, 1),   -- Sel
(8, 19, 1),   -- Poivre
(8, 28, 30),  -- 30g fromage

-- Recette 9: Lasagnes bolognaise
(9, 2, 500),  -- 500g boeuf
(9, 6, 4),    -- 4 tomates
(9, 12, 2),   -- 2 oignons
(9, 13, 3),   -- 3 gousses d'ail
(9, 33, 300), -- 300g pates lasagnes
(9, 28, 200), -- 200g fromage
(9, 18, 1),   -- Sel
(9, 19, 1),   -- Poivre

-- Recette 10: Soupe de legumes
(10, 11, 4),  -- 4 carottes
(10, 12, 2),  -- 2 oignons
(10, 15, 2),  -- 2 courgettes
(10, 16, 100), -- 100g epinards
(10, 17, 200), -- 200g brocoli
(10, 25, 1),  -- Bouillon
(10, 18, 1),  -- Sel
(10, 19, 1);  -- Poivre

-- Insertion des recettes favorites
-- User 2 (Marie) aime les recettes 1, 3, 5
INSERT INTO user_recettes_favorites (user_id, recette_id) VALUES
(2, 1),
(2, 3),
(2, 5),

-- User 3 (Pierre) aime les recettes 1, 6, 9
(3, 1),
(3, 6),
(3, 9),

-- User 4 (Sophie) aime les recettes 2, 5, 7
(4, 2),
(4, 5),
(4, 7),

-- User 1 (Jean - ADMIN) aime les recettes 3, 9
(1, 3),
(1, 9),

-- User 5 (Thomas - ADMIN) aime les recettes 1, 5, 6
(5, 1),
(5, 5),
(5, 6);

-- Vérification des données insérées
SELECT 'Users insérés: ' || COUNT(*) FROM users;
SELECT 'Ingrédients insérés: ' || COUNT(*) FROM ingredients;
SELECT 'Recettes insérées: ' || COUNT(*) FROM recettes;
SELECT 'Relations ingrédients-recettes: ' || COUNT(*) FROM ingredient_par_recette;
SELECT 'Recettes favorites: ' || COUNT(*) FROM user_recettes_favorites;
