/**
 * Ingrediente.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    id_ingrediente:{
      type: 'number',
      autoIncrement:true,
      columnName:'idIngrediente'
    },

    nombre_ingred:{
      type: 'string',
      required:true,
      columnName:'nombreIngrediente',
      maxLength:50
    },
  
    receta:{
      model: 'receta'
    }
  },

};

