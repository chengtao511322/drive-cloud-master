import request from '@/utils/request'

                    
// 分页查询运营商参数设置表列表
export function listPageOperatorSettinng(query) {
  return request({
    url: '/admin/operatorSettinng/pageList',
    method: 'post',
    data: query
  })
}


// 分页查询运营商参数设置表列表
export function listOperatorSettinng(query) {
  return request({
    url: '/admin/operatorSettinng/findList',
    method: 'post',
    data: query
  })
}

// 查询运营商参数设置表详细
export function getOperatorSettinng(id) {
  return request({
    url: '/admin/operatorSettinng/' + id,
    method: 'get'
  })
}

// 新增运营商参数设置表
export function addOperatorSettinng(data) {
  return request({
    url: '/admin/operatorSettinng',
    method: 'post',
    data: data
  })
}

// 修改运营商参数设置表
export function updateOperatorSettinng(data) {
  return request({
    url: '/admin/operatorSettinng',
    method: 'put',
    data: data
  })
}

// 删除运营商参数设置表
export function delOperatorSettinng(id) {
  return request({
    url: '/admin/operatorSettinng/' + id,
    method: 'delete'
  })
}


// 状态启用/停用运营商参数设置表
export function changeStatus(data) {
  return request({
    url: '/admin/operatorSettinng/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出运营商参数设置表
export function exportOperatorSettinng(query) {
  return request({
    url: '/admin/operatorSettinng/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}