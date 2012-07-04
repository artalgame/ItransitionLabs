using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using MvcApplicationModels.Models;

namespace MvcApplicationModels.Controllers
{
    public class HomeController : Controller
    {
        public ActionResult Index()
        {
            DataManager manager = new DataManager();

            ViewData.Model = manager.GetItems();
            ViewBag.Items = manager.GetItems();
            ViewBag.LinkName = "Моя текстовая ссылка";

            ViewBag.Message = "Welcome to ASP.NET MVC!";

            return RedirectToAction("List");
        }

        [HttpGet]
        public ActionResult Edit(Guid id)
        {
            DataManager db = new DataManager();
            return View(db.GetItem(id));
        }

        [HttpPost]
        public ActionResult Edit(Item obj)
        {
            if (ModelState.IsValid)
            {
                DataManager db = new DataManager();
                db.SaveItem(obj);
                return RedirectToAction("Index");
            }
            else
            {
                return View();
            }
        }
        [HttpPost]
        public ActionResult Category(Category obj)
        {
            DataManager db = new DataManager();
            Category old = db.GetCategory(obj.CategoryID);
            UpdateModel(old);
            db.Save();
            return RedirectToAction("List");
        }

        [HttpGet]
        public ActionResult Category(Guid id)
        {
            DataManager db = new DataManager();
            return View(db.GetCategory(id));
        }
        public ActionResult List()
        {
            DataManager db = new DataManager();
            ViewBag.Categories = db.GetCategories();
            return View();
        }

        public ActionResult About()
        {
            return View();
        }
    }
}
