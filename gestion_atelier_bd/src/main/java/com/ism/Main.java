package com.ism;
import java.util.Scanner;

import com.ism.entities.ArticleConfection;
import com.ism.entities.Categorie;
import com.ism.repository.ITables;
import com.ism.repository.bd.CategorieRepository;
import com.ism.repository.bd.DataBase;
import com.ism.repository.list.TableArticleConfection;
import com.ism.repository.list.TableCategories;
import com.ism.services.ArticleConfectionService;
import com.ism.services.ArticleConfectionServiceImpl;
import com.ism.services.CategorieService;
import com.ism.services.CategorieServiceImpl;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
         Scanner scanner =new Scanner(System.in);
         DataBase dataBase=new Mysql();
         ITables<Categorie> categorieRepository=new CategorieRepository(dataBase);
         CategorieService categorieServiceImpl=new CategorieServiceImpl(categorieRepository);
        //ITables<Categorie> categorieRepository = new TableCategories();
        //CategorieService categorieService = new CategorieServiceImpl(categorieRepository);

        ITables<ArticleConfection> articleRepository = new TableArticleConfection();
        ArticleConfectionService articleService = new ArticleConfectionServiceImpl(articleRepository);

        int choice;

        do {
            System.out.println("-------MENU GENERAL-------");
            System.out.println("1----Menu Catégories-----");
            System.out.println("2----Menu Articles de Confection------");
            System.out.println("3----Quitter------");

            choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1:
                    displayCategorieMenu(categorieServiceImpl);
                    break;
                case 2:
                    displayArticleMenu(articleService, scanner);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
                    break;
            }
        } while (true);
    }

    public static void displayCategorieMenu(CategorieService categorieService) {
        int choix;

        do {
            System.out.println("-------MENU CATÉGORIES-------");
            System.out.println("1----Ajouter une catégorie-----");
            System.out.println("2----Lister les catégories------");
            System.out.println("3----Modifier une catégorie------");
            System.out.println("4----Supprimer une catégorie-----");
            System.out.println("5----Rechercher une catégorie----");
            System.out.println("6----Supprimer plusieurs catégories------");
            System.out.println("7----Revenir au menu principal------");

            choix = scanner.nextInt();
            scanner.nextLine(); 

            switch (choix) {
                case 1:
                    System.out.println("Entrer le libellé de la catégorie :");
                    String libelleCategorie = scanner.nextLine();
                    Categorie nouvelleCategorie = new Categorie(libelleCategorie);
                    categorieService.add(nouvelleCategorie);
                    break;
                case 2:
                    System.out.println("Liste de toutes les catégories :");
                    categorieService.getAll().forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Entrer l'ID de la catégorie à modifier :");
                    int idCategorie = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Entrer le nouveau libellé :");
                    String newLibelleCategorie = scanner.nextLine();
                    Categorie categorieAModifier = new Categorie(newLibelleCategorie);
                    categorieAModifier.setId(idCategorie);
                    int updateResult = categorieService.update(categorieAModifier);
                    if (updateResult >= 1) {
                        System.out.println("Catégorie mise à jour avec succès.");
                    } else {
                        System.out.println("La catégorie n'a pas pu être mise à jour.");
                    }
                    break;
                case 4:
                    System.out.println("Entrer l'ID de la catégorie à supprimer :");
                    int idToDelete = scanner.nextInt();
                    scanner.nextLine();
                    int deleteResult = categorieService.remove(idToDelete);
                    if (deleteResult == 1) {
                        System.out.println("Catégorie supprimée avec succès.");
                    } else {
                        System.out.println("La catégorie n'a pas pu être supprimée.");
                    }
                    break;
                case 5:
                    System.out.println("Entrer l'ID de la catégorie à afficher :");
                    int idToShow = scanner.nextInt();
                    scanner.nextLine();
                    Categorie categorieToShow = categorieService.show(idToShow);
                    if (categorieToShow != null) {
                        System.out.println("Catégorie : " + categorieToShow);
                    } else {
                        System.out.println("Catégorie non trouvée.");
                    }
                    break;
                case 6:
                    System.out.println("Entrer le nombre d'IDs à supprimer :");
                    int count = scanner.nextInt();
                    scanner.nextLine();
                    int[] idsToRemove = new int[count];
                    for (int i = 0; i < count; i++) {
                        System.out.println("Entrer l'ID " + (i + 1) + " à supprimer :");
                        idsToRemove[i] = scanner.nextInt();
                        scanner.nextLine();
                    }
                    int[] notDeleted = categorieService.remove(idsToRemove);
                    if (notDeleted.length == 0) {
                        System.out.println("Toutes les catégories ont été supprimées avec succès.");
                    } else {
                        System.out.println("Les catégories suivantes n'ont pas pu être supprimées :");
                        for (int id : notDeleted) {
                            System.out.println("ID " + id);
                        }
                    }
                    break;
                case 7:
                    return; 
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
                    break;
            }
        } while (true);
    }
    public static void displayArticleMenu(ArticleConfectionService articleService, Scanner scanner) {
        int choix;
    
        do {
            System.out.println("-------MENU ARTICLES DE CONFECTION-------");
            System.out.println("1----Ajouter un article de confection-----");
            System.out.println("2----Lister les articles de confection------");
            System.out.println("3----Modifier un article de confection------");
            System.out.println("4----Supprimer un article de confection-----");
            System.out.println("5----Rechercher un article de confection----");
            System.out.println("6----Supprimer plusieurs articles de confection------");
            System.out.println("7----Revenir au menu principal------");
    
            choix = scanner.nextInt();
            scanner.nextLine();
    
            switch (choix) {
                case 1:
                    System.out.println("Entrer le libellé de l'article de confection :");
                    String libelleArticle = scanner.nextLine();
                    System.out.println("Entrer le prix de l'article de confection :");
                    double prixArticle = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Entrer la quantité de l'article de confection :");
                    double quantiteArticle = scanner.nextDouble();
                    scanner.nextLine();
                    ArticleConfection nouvelArticle = new ArticleConfection(libelleArticle, prixArticle, quantiteArticle);
                    int ajoutResult = articleService.add(nouvelArticle);
                    if (ajoutResult == 1) {
                        System.out.println("Article de confection ajouté avec succès.");
                    } else {
                        System.out.println("Erreur lors de l'ajout de l'article de confection.");
                    }
                    break;
                case 2:
                    System.out.println("Liste de tous les articles de confection :");
                    articleService.getAll().forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Entrer l'ID de l'article de confection à modifier :");
                    int idArticle = scanner.nextInt();
                    scanner.nextLine();
                    ArticleConfection articleAModifier = articleService.show(idArticle);
                    if (articleAModifier != null) {
                        System.out.println("Article à modifier : " + articleAModifier);
    
                        System.out.println("Entrer le nouveau libellé :");
                        String newLibelleArticle = scanner.nextLine();
    
                        System.out.println("Entrer le nouveau prix :");
                        double newPrixArticle = scanner.nextDouble();
                        scanner.nextLine();
    
                        System.out.println("Entrer la nouvelle quantité :");
                        double newQuantiteArticle = scanner.nextDouble();
                        scanner.nextLine();
    
                        articleAModifier.setLibelle(newLibelleArticle);
                        articleAModifier.setPrix(newPrixArticle);
                        articleAModifier.setQte(newQuantiteArticle);
    
                        int updateResult = articleService.update(articleAModifier);
                        if (updateResult >= 1) {
                            System.out.println("Article de confection mis à jour avec succès.");
                        } else {
                            System.out.println("L'article de confection n'a pas pu être mis à jour.");
                        }
                    } else {
                        System.out.println("Article de confection non trouvé.");
                    }
                    break;
                case 4:
                    System.out.println("Entrer l'ID de l'article de confection à supprimer :");
                    int idToDelete = scanner.nextInt();
                    scanner.nextLine();
                    int deleteResult = articleService.remove(idToDelete);
                    if (deleteResult == 1) {
                        System.out.println("Article de confection supprimé avec succès.");
                    } else {
                        System.out.println("L'article de confection n'a pas pu être supprimé.");
                    }
                    break;
                case 5:
                    System.out.println("Entrer l'ID de l'article de confection à afficher :");
                    int idToShow = scanner.nextInt();
                    scanner.nextLine();
                    ArticleConfection articleToShow = articleService.show(idToShow);
                    if (articleToShow != null) {
                        System.out.println("Article de confection : " + articleToShow);
                    } else {
                        System.out.println("Article de confection non trouvé.");
                    }
                    break;
                case 6:
                    System.out.println("Entrer le nombre d'IDs à supprimer :");
                    int count = scanner.nextInt();
                    scanner.nextLine();
                    int[] idsToRemove = new int[count];
                    for (int i = 0; i < count; i++) {
                        System.out.println("Entrer l'ID " + (i + 1) + " à supprimer :");
                        idsToRemove[i] = scanner.nextInt();
                        scanner.nextLine();
                    }
                    int[] notDeleted = articleService.remove(idsToRemove);
                    if (notDeleted.length == 0) {
                        System.out.println("Tous les articles de confection ont été supprimés avec succès.");
                    } else {
                        System.out.println("Les articles de confection suivants n'ont pas pu être supprimés :");
                        for (int id : notDeleted) {
                            System.out.println("ID " + id);
                        }
                    }
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
                    break;
            }
        } while (true);
    }
}