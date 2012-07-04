using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace MvcApplicationModels.Models
{
    public class DataManager
    {
        private ItemsListDBEntities db;
        
        public DataManager()
        {
            db = new ItemsListDBEntities();
        }

        public IQueryable<Item> GetItems()
        {
            return db.Items;
        }

        public Item GetItem(Guid id)
        {
            return db.Items.SingleOrDefault<Item>(x => x.ItemID == id);
        }

        public void SaveItem(Item savedItem)
        {
            Item old = GetItem(savedItem.ItemID);
            old.Title = savedItem.Title;
            old.Quantity = savedItem.Quantity;
            db.SaveChanges();

        }
        public IQueryable<Category> GetCategories()
        {
            return db.Categories;
        }
        public Category GetCategory(Guid id)
        {
            return GetCategories().SingleOrDefault<Category>(x => x.CategoryID == id);
        }
        public void SaveCategory(Category obj)
        {
            Category old = GetCategory(obj.CategoryID);
            old.Title = obj.Title;
        }
        public void Save()
        {
            db.SaveChanges();
        }
    }
}