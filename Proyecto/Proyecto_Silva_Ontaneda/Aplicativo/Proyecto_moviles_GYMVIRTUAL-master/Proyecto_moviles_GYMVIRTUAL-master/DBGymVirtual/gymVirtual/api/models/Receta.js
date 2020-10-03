/**
 * Receta.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    id_receta:{
      type: 'number',
      autoIncrement:true,
      columnName:'idReceta'
    },

    nombre_rec:{
      type: 'string',
      required:true,
      columnName:'nombreReceta',
      maxLength:50
    },

    imagen_rec:{
      type: 'string',
      required:false,
      columnName:'urlImage'
    },

    preparacion_rec:{
      type: 'string',
      required: true,
      columnName: 'preparacionRec',
      maxLength: 50000
    },

    ingredientes:{
      collection: 'ingrediente',
      via: 'receta'
    }, 

    recetasUsuario:{
      collection: 'recetaUsuario',
      via:'receta'
    }

  },

};

