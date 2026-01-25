package com.example.webflux.domain.listings.models;

public enum ListingStatusReview {
    PENDING, SUCCESFULL, ISSUE, REFUSED
}

/**
 * Estados de listings o publicaciones por el usuario
 * 
 * - PENDING -> En espera en que un admin o directivo revise la publicación del
 * vendedor
 * 
 * - SUCCESFULL -> El admin o directivo ha aprovado tu publicacion y puedes
 * publicarlo en la app
 * 
 * - ISSUE -> EL admin o directivo te notifican el porque la publicación tiene
 * problemas y que deberias de cambiar
 * 
 * - REFUSED -> El admin o directivo rechazo tu publicación ya sea por
 * inapropiado o violando las politicas
 * 
 * Arbol de estados
 * 
 * Estado inicial PENDING -> ISSUE -> SUCCESFULL  O REFUSED
 * 
 * O: 
 * 
 * ESTADO INICIAL PENDING -> SUCCESFULL O REFUSED // rechazado o aceptado directamente sin pasar por ISSUE
 * 

 * 
 */